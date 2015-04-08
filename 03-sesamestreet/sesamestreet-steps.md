# Stage I: Create web application
i. Create webapp (create-app sesame-street) 

# Stage II: Create domain
ii. Create the domain classes (create-domain-class sesamestreet.Muppet)

# Stage II: Create domain
iii. Define properties, constants, constraints and mappings

~~~
package sesamestreet

class Muppet {

	final static Character MALE = 'M'
	final static Character FEMALE = 'F'
	final static Character ANY = '%'

	String name
	String about
	String color
	Character gender
	boolean hasFur = Boolean.FALSE
	String picture
	static hasMany = [clips: String]

	static constraints = {
		name nullable: true, size: 2..15
		about nullable: true, size: 0..1000
		color blank: false
		gender nullable: true
		picture nullable: true 
		clips nullable: true
	}

	static mapping = {
		table 'muppets', 
			id_column: 'muppet_id',
			about: [length: 1000]
		clips joinTable: 
			[name: 'muppets_videoclips', key: 'muppet_id', column: 'clip_url', type: "text"]
	}
}
~~~

# Stage II: Create domain
vi. Let’s refactor and write down a Unit Test

~~~
package sesamestreet

import grails.test.mixin.*
import org.junit.*

@TestFor(Muppet)
class MuppetUnitTests {

	void testSomeCharacters() {

		def ernie = new Muppet(name: 'ernie', color: 'orange', gender: Muppet.MALE)
		assert ernie.validate()

		def bert = new Muppet(name:'bert', gender: Muppet.MALE)
		assert !bert.validate()
		assert bert.errors.errorCount == 1

	}
}
~~~

# Stage II: Create domain
v. Run Unit tests (test-app) 

# Stage II: Create domain
vi. Write an integration test (create-integration-test) and run it (test-app integration:) 

~~~
package sesamestreet

import static org.junit.Assert.*
import org.junit.*
import grails.validation.ValidationException

class MuppetTests {

	@Test
	void testSaveMuppets() {
		def ernie = new Muppet(name: 'ernie', color: 'orange', gender: Muppet.MALE)
		
		def initialSize = Muppet.count
		ernie.save()
		assert initialSize + 1 == Muppet.count

		def bert = new Muppet(name: 'bert', gender: Muppet.MALE)
		bert.save()
		assert 1 == bert.errors.errorCount
		
		//other way to do so throwing an exception
		try {
			bert.save(failOnError:true)
			fail 'cannot get here'
		} catch (ValidationException e) {
			assert 1 == bert.errors.errorCount
		}
	}
}
~~~

# Stage II: Create domain
vii. Let’s change database config and run again (test-app integration:) 

~~~
config/DataSource.groovy
~~~
dataSource {
    pooled = true
    jmxExport = true
    driverClassName = "org.h2.Driver"
    username = "sa"
    password = ""
}
hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = false
//    cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory' // Hibernate 3
    cache.region.factory_class = 'org.hibernate.cache.ehcache.EhCacheRegionFactory' // Hibernate 4
    singleSession = true // configure OSIV singleSession mode
    flush.mode = 'manual' // OSIV session flush mode outside of transactional context
}

// environment specific settings
environments {
    development {
        dataSource {
            dbCreate = "create-drop" // one of 'create', 'create-drop', 'update', 'validate', ''
            url = "jdbc:h2:mem:devDb;MVCC=TRUE;LOCK_TIMEOUT=10000;DB_CLOSE_ON_EXIT=FALSE"
			logSql = true;
        }
    }
    test {
        dataSource {
            dbCreate = "update"
            url = "jdbc:h2:mem:testDb;MVCC=TRUE;LOCK_TIMEOUT=10000;DB_CLOSE_ON_EXIT=FALSE"
			logSql = true;
        }
    }
    production {
        dataSource {
            dbCreate = "update"
            url = "jdbc:h2:prodDb;MVCC=TRUE;LOCK_TIMEOUT=10000;DB_CLOSE_ON_EXIT=FALSE"
            properties {
               // See http://grails.org/doc/latest/guide/conf.html#dataSource for documentation
               jmxEnabled = true
               initialSize = 5
               maxActive = 50
               minIdle = 5
               maxIdle = 25
               maxWait = 10000
               maxAge = 10 * 60000
               timeBetweenEvictionRunsMillis = 5000
               minEvictableIdleTimeMillis = 60000
               validationQuery = "SELECT 1"
               validationQueryTimeout = 3
               validationInterval = 15000
               testOnBorrow = true
               testWhileIdle = true
               testOnReturn = false
               jdbcInterceptors = "ConnectionState"
               defaultTransactionIsolation = java.sql.Connection.TRANSACTION_READ_COMMITTED
            }
        }
    }
}
~~~
# Stage III: Create business layer
viii. We have to create the service skeleton (create-service sesamestreet.MuppetDiscovery) 

~~~
package sesamestreet

import java.util.Set;

class MuppetDiscoveryService {

	Muppet lookForYourPerfectMuppet(Muppet desiredTraitsExample) {
	}

	Set<Muppet> lookForRelatedMuppets(Muppet desiredTraitsExample) {
	}
}
~~~

# Stage III: Create business layer
ix. Let’s write the unit test first and run it (test-app-unit sesamestreet.MuppetDiscoveryServiceUnitTests)

~~~
package sesamestreet

import grails.test.mixin.*
import org.junit.*

@TestFor(MuppetDiscoveryService)
@Mock(Muppet)
class MuppetDiscoveryServiceUnitTests {


	@Before
	void prepareCharacters(){
		def muppets = [
			new Muppet(name: 'Ernie', color: "orange", hasFur: false, gender: Muppet.MALE),
			new Muppet(name: 'Bert', color: "yellow", hasFur: false, gender: Muppet.MALE),
			new Muppet(name: 'Elmo', color: "red", hasFur: true, gender: Muppet.MALE),
			new Muppet(name: 'Grover', color: "blue", hasFur: true, gender: Muppet.MALE),
			new Muppet(name: 'Cookie Monster', color: "blue", hasFur: true, gender: Muppet.MALE),
			new Muppet(name: 'Zoe', color:"orange", hasFur: true, gender: Muppet.FEMALE)
		]

		muppets.each{ 
			it.save()
		}
	}

	@Test
	void testLookForPerfectAndRelatedMuppets() {
		def desiredTraits = new Muppet(color: "orange",  hasFur: false)
		
		def perfectMuppet = service.lookForYourPerfectMuppet(desiredTraits)
		assert perfectMuppet != null
		assert 'Ernie' == perfectMuppet.name
		
		def relatedMuppets = service.lookForRelatedMuppets(desiredTraits)
		assert 3 == relatedMuppets.size()
	}
}
~~~

# Stage III: Create business layer
x. Implement this service and its logging

~~~
package sesamestreet

import java.util.Set;

class MuppetDiscoveryService {

	Muppet lookForYourPerfectMuppet(Muppet desiredTraitsExample) {
		log.debug "looking perfect muppet with these traits ${desiredTraitsExample.dump()}"
		def perfectMuppet = Muppet.find(desiredTraitsExample)
	}

	Set<Muppet> lookForRelatedMuppets(Muppet desiredTraitsExample) {
		log.debug "looking related muppets with these traits ${desiredTraitsExample.properties}"
		def relatedMuppets =
				Muppet.findAllByColorLikeOrHasFur(
					desiredTraitsExample.color, desiredTraitsExample.hasFur)
				
		return relatedMuppets
	}
}
~~~

# Stage III: Create business layer
xi. Change log4j configuration 

~~~
config/Config.groovy
~~~
// log4j configuration
log4j = {
    // Example of changing the log pattern for the default console appender:
    
    appenders {
        console name:'stdout', layout:pattern(conversionPattern: '%c{2} %m%n')
    }

    error  'org.codehaus.groovy.grails.web.servlet',        // controllers
           'org.codehaus.groovy.grails.web.pages',          // GSP
           'org.codehaus.groovy.grails.web.sitemesh',       // layouts
           'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
           'org.codehaus.groovy.grails.web.mapping',        // URL mapping
           'org.codehaus.groovy.grails.commons',            // core / classloading
           'org.codehaus.groovy.grails.plugins',            // plugins
           'org.codehaus.groovy.grails.orm.hibernate',      // hibernate integration
           'org.springframework',
           'org.hibernate',
           'net.sf.ehcache.hibernate'
	
	debug  'grails.app',
		   'sesamestreet'
}
~~~

# Stage III: Create business layer
xii. create an integration (create-integration-test) test and run all tests (test-app)

~~~
package sesamestreet

import grails.test.mixin.*
import org.junit.*


class MuppetDiscoveryServiceTests {

	def muppetDiscoveryService
	
	@Before
	void prepareCharacters(){
		def muppets = [
			new Muppet(name: 'Ernie', color: "orange", hasFur: false, gender: Muppet.MALE),
			new Muppet(name: 'Bert', color: "yellow", hasFur: false, gender: Muppet.MALE),
			new Muppet(name: 'Elmo', color: "red", hasFur: true, gender: Muppet.MALE),
			new Muppet(name: 'Grover', color: "blue", hasFur: true, gender: Muppet.MALE),
			new Muppet(name: 'Cookie Monster', color: "blue", hasFur: true, gender: Muppet.MALE),
			new Muppet(name: 'Zoe', color:"orange", hasFur: true, gender: Muppet.FEMALE)
		]

		muppets.each{ 
			it.save()
		}
	}

	@Test
	void testLookForPerfectAndRelatedMuppets() {
		def desiredTraits = new Muppet(color: "orange",  hasFur: false)
		
		def perfectMuppet = muppetDiscoveryService.lookForYourPerfectMuppet(desiredTraits)
		assert perfectMuppet != null
		assert 'Ernie' == perfectMuppet.name
		
		def relatedMuppets = muppetDiscoveryService.lookForRelatedMuppets(desiredTraits)
		assert 3 == relatedMuppets.size()
	}
}
~~~

# Stage IV: Create web layer
xiii. Create a controller (create-controller sesamestreet.MuppetDiscovery)

~~~
package sesamestreet

class MuppetDiscoveryController {

  def muppetDiscoveryService
	def desiredTraits
	
	static scope = 'session'
	
    def index() {
		render view: 'choosemuppettraits'
	}
	
	def search(){
		desiredTraits = new Muppet(params)
		
		if(!desiredTraits.validate()) {
			return redirect (action: 'index')
		}
		
		log.debug "searching perfect match for ${desiredTraits.properties}..."
		flash.msg = "Congrats We've found the perfect muppet for you!"
		def targetView='found'
		def perfectMuppet = muppetDiscoveryService.lookForYourPerfectMuppet desiredTraits
		def relatedMuppets
		
		if(!perfectMuppet){
			log.debug "searching related muppets for  ${desiredTraits.properties}..."
			flash.msg = "Sorry We haven't found the perfect muppet for you!"
			targetView='notfound'
			relatedMuppets = muppetDiscoveryService.lookForRelatedMuppets desiredTraits
		}
		
		render view:targetView , model:[muppet:perfectMuppet,muppets:relatedMuppets]
	}
}
~~~

# Stage IV: Create web layer
xiv. Refactor an write down test unit controller first of all

~~~
package sesamestreet

import grails.test.mixin.*
import org.junit.*

@TestFor(MuppetDiscoveryController)
@Mock([MuppetDiscoveryService, Muppet])
class MuppetDiscoveryControllerUnitTests {

	@Before
	void prepareCharacters(){
		new Muppet(name: 'Ernie', color: "orange", hasFur: false, gender: Muppet.MALE).save()
	}
	
	@Test
    void testFoundPerfectMuppet() {
		request.method = 'POST'
		def params = [color: 'orange', hasFur: false ]
		controller.params.putAll(params)
	
		controller.search()
		
		assert '/muppetDiscovery/found' == view
		assert flash.msg.startsWith('Congrats')
		assert 'Ernie' == model.muppet.name
    }
	
	@Test
	void testNotFoundPerfectMuppet() {
		request.method = 'POST'
		def params = [color: 'yellow', hasFur: false ]
		controller.params.putAll(params)
		
		controller.search()
		
		assert '/muppetDiscovery/notfound' == view
		assert flash.msg.startsWith('Sorry')
		assert 1 == model.muppets.size()
	}
}
~~~

# Stage IV: Create web layer
xv. Create integration test for controller too!

~~~
package sesamestreet

import grails.test.mixin.*
import org.junit.*

class MuppetDiscoveryControllerTests {
	
	@Before
	void prepareCharacters(){
		new Muppet(name: 'Ernie', color: "orange", hasFur: false, gender: Muppet.MALE)
			.save()
	}
	
	@Test
    void testFoundPerfectMuppet() {
		def controller = new MuppetDiscoveryController()
		
		controller.request.method = 'POST'
		def params = [color: 'orange', hasFur: false ]
		controller.params.putAll(params)
	
		controller.search()
		
		assert '/muppetDiscovery/found' == controller.modelAndView.viewName
		assert controller.flash.msg.startsWith('Congrats')
		assert 'Ernie' == controller.modelAndView.model.muppet.name
    }
	
	@Test
	void testNotFoundPerfectMuppet() {
		def controller = new MuppetDiscoveryController()
		
		controller.request.method = 'POST'
		def params = [color: 'yellow', hasFur: false ]
		controller.params.putAll(params)
		
		controller.search()
		
		assert '/muppetDiscovery/notfound' == controller.modelAndView.viewName
		assert controller.flash.msg.startsWith('Sorry')
		assert 1 == controller.modelAndView.model.muppets.size()
	}
}
~~~

# Stage IV: Create web layer
xvi. Let’s write down the initial GSP page of searches

~~~
views/muppetDiscovery/choosemuppettraits.gsp
~~~
<%@ page contentType="text/html;charset=UTF-8" import="sesamestreet.Muppet" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<meta name="layout" content="main" />
<title>.:Muppet Discovery:.</title>
</head>
<body>
	<div class="content">
		<h3 class="fieldcontain" align="center"><g:message code="muppets.discovery.welcome" 
			default="Enter your favourite traits and you'll find out the perfect muppet for you!"/></h3>
			
		<g:form action="search">
			<div class="fieldcontain ${hasErrors(bean:desiredTraits,field:'color','errors')}">	
				<label for="color"> <g:message	code="muppet.color.label" default="Color" /></label>
				<g:select name="color" from="${['red','orange','yellow','blue','green','purple']}" value="${desiredTraits?.color}" 
					noSelection="['':'-Choose color-']"/>
			</div>
			<div class="fieldcontain ${hasErrors(bean:desiredTraits,field:'hasFur','errors')}">	
				<label for="hasFur"> <g:message code="muppet.hasfur.label" default="Has fur?" /></label>
				<g:checkBox name="hasFur" value="${desiredTraits?.hasFur}"/>
				
			</div>
			<div class="fieldcontain  ${hasErrors(bean:desiredTraits,field:'gender','errors')}">	
				<label for="sexo"> <g:message	code="muppet.gender.label" default="Gender" /></label>
				<g:message	code="muppet.gender.male.label" default="Male" />
					<g:radio name="gender" value="${Muppet.MALE}"  checked="${desiredTraits?.gender==Muppet.MALE}"/>
				<g:message	code="muppet.gender.female.label" default="Female" />
					<g:radio name="gender" value="${Muppet.FEMALE}"  checked="${desiredTraits?.gender==Muppet.FEMALE}"/>
				<g:renderErrors bean="${desiredTraits}"  field="gender"/>
			</div>
			<br/>
			<div align="right" class="fieldcontain" >
				<fieldset>
				<input type="submit" value="${message(code: 'default.button.search.label', default: 'Search')}" class="buttons" />
				</fieldset>
			</div>
					
		</g:form>
		<div class="${hasErrors(bean:desiredTraits,'errors')}"><g:renderErrors bean="${desiredTraits}" /></div>
	</div>
</body>
</html>
~~~

# Stage IV: Create web layer
xvii. GSP page for the best matched muppet 

~~~
views/muppetDiscovery/found.gsp
~~~
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
<meta name="layout" content="main"/>
<title>.:Muppet Discovery:.</title>
</head>
<body>
  <div class="content">
  	<h3 class="fieldcontain" align="center">${flash?.msg}</h3>
  			<div class="fieldcontain">
  				<label for="picture" ><g:message code="muppet.picture.label" default="Picture" /></label>
				<g:img name="picture" dir="images/pictures"  file="${muppet?.picture}" readonly="readonly" width="183px" />
			</div>
			<div class="fieldcontain">
				<label for="name"> <g:message	code="muppet.name.label" default="Name" /></label>
				<g:textField name="name" value="${muppet?.name}" readonly="readonly" />
			</div>
			<div class="fieldcontain">
				<label for="name"> <g:message	code="muppet.about.label" default="About" /></label>
				<g:textArea name="about" value="${muppet?.about}" readonly="readonly" />
			</div>			
			<div class="fieldcontain">
				<label for="hasFur"> <g:message	code="muppet.hasFur.label" default="Has Far?" /></label>
				<g:textField name="hasFur" value="${muppet?.hasFur}" readonly="readonly"  />
			</div>
			<div class="fieldcontain">
				<label for="gender"> <g:message	code="muppet.gender.label" default="Gender" /></label>
				<g:textField name="gender" value="${muppet?.gender}" readonly="readonly" />
			</div>
			<div class="fieldcontain">
				<label for="clips"> <g:message	code="muppet.clips.label" default="Videoclips" /></label>
				<g:each var="clip" in="${muppet?.clips}" status="counter">
					<img src="http://img.youtube.com/vi/${clip}/default.jpg">
					<a href="${createLink(url:'https://www.youtube.com/watch?v='+clip)}" target="_blank">clip${counter+1}</a>
				</g:each> 
			</div>
			<br/>
		<div align="right" class="fieldcontain">
			<fieldset><g:link controller="muppetDiscovery" class="buttons">
					<g:message code="default.button.back.label" default="Back" />
				</g:link></fieldset>
		</div>
  </div>
</body>
</html>
~~~

# Stage IV: Create web layer
xviii. GSP page for no matched muppets

~~~
views/muppetDiscovery/notfound.gsp
~~~
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<meta name="layout" content="main" />
<title>.:Muppet Discovery:.</title>
</head>
<body>
	<div class="content">
		<h3 class="fieldcontain" align="center">${flash?.msg}</h3>
		<p align="center">But, we think these other muppets are related to your interests, take a look!</p>
		<br />
		<table border="0" class="albumsTable">
			<tr>
				<th>Picture</th>
				<th>Name</th>
				<th>Gender</th>
				<th>Has fur?</th>
			</tr>
			<g:each var="muppet" in="${muppets}">
				<tr>
					<td><g:img name="picture" dir="images/pictures"  file="${muppet?.picture}" 
						readonly="readonly" width="61px" /></td>
					<td>${muppet.name}</td>
					<td>${muppet.gender}</td>
					<td>${muppet.hasFur}</td>
				</tr>
			</g:each>
		</table>
		<div align="right" class="fieldcontain">
			<fieldset>
				<g:link controller="muppetDiscovery" class="buttons">
					<g:message code="default.button.back.label" default="back" />
				</g:link>
			</fieldset>
		</div>
	</div>
</body>
</html>
~~~

# Stage IV: Create web layer
xix. Presentation fine tuning (main.gsp/main.css)

~~~
views/layout/main.gsp
~~~
<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<title><g:layoutTitle default="Grails"/></title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="shortcut icon" href="${resource(dir: 'images', file: 'favicon.ico')}" type="image/x-icon">
		<link rel="apple-touch-icon" href="${resource(dir: 'images', file: 'apple-touch-icon.png')}">
		<link rel="apple-touch-icon" sizes="114x114" href="${resource(dir: 'images', file: 'apple-touch-icon-retina.png')}">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}" type="text/css">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'mobile.css')}" type="text/css">
		<g:layoutHead/>
		<r:layoutResources />
	</head>
	<body>
		<div id="grailsLogo" role="banner" ><h3>&nbsp;<img src="${resource(dir: 'images', file: 'sesamestreet_logo.png')}" alt="Sesame Street"/>&nbsp;&nbsp;Muppet Discovery</h3></div>
		<g:layoutBody/>
		<div class="footer" role="contentinfo"><b>Greach 2014.</b> Workshop by Fernando Redondo <a href="http://twitter.com/pronoide_fer">(@pronoide_fer)</a></div>
		<div id="spinner" class="spinner" style="display:none;"><g:message code="spinner.alt" default="Loading&hellip;"/></div>
		<g:javascript library="application"/>
		<r:layoutResources />
	</body>
</html>
~~~

~~~
web-app/css/main.gsp
~~~
.footer {
	background: #00a76d;
	color: #000;
	clear: both;
	font-size: 0.8em;
	margin-top: 1.5em;
	padding: 1em;
	min-height: 1em;
}

.required-indicator {
	color: #ffd729;
	display: inline-block;
	font-weight: bold;
	margin-left: 0.3em;
	position: relative;
	top: 0.1em;
}
~~~

# Stage V: Run Webapp
xx. Start the web application (run-app)

# Stage V: Run Webapp
xxi. Load data and map default controller

~~~
conf/BootStrap.groovy
~~~
import grails.util.Environment
import sesamestreet.Muppet

class BootStrap {

    def init = { servletContext ->
		switch (Environment.current) {
			case Environment.DEVELOPMENT:
					
        def muppets=[
          new Muppet(name:'Ernie', gender: Muppet.MALE, color: "orange", hasFur: false, picture: 'ernie.jpg',
            about:'''Some people call me wise, playful and naturally outgoing. I like to have fun, especially with my best friend Bert. I like to ask a lot of questions and I am great at explaining things, but can sometimes be a bit too smart for my own good.''',
            clips: ['Mh85R-S-dh8','pigcahytIH8']),
          new Muppet(name:'Bert', gender: Muppet.MALE, color: "yellow", hasFur: false, picture: 'bert.jpg',
            about:'''I am the long-suffering sidekick of Ernie. I am more mature, analytical, and the voice of reason in our relationship. Some might view me as eccentric because I collect bottle caps and paper clips, play the tuba, and love Bernice, my pet pigeon. I'm not always a willing participant in Ernie's escapades because I always sense that the tables are destined to be turned on me, or that I will end up with the short end of the stick! In the end, however, I always forgive Ernie, forever remaining his "old buddy."''',
            clips: ['-oD1xuAJMak','pigcahytIH8']),
          new Muppet(name:'Elmo', gender: Muppet.MALE, color: "red", hasFur: true, picture: 'elmo.jpg',
            about:'''Elmo is 3 ' years old. Elmo has red fur and Elmo loves playing with his friends Zoe and Abby. Elmo loves riding on this tricycle and being tickled. Elmo also has a pet goldfish, Dorothy. Elmo's friends tell Elmo that Elmo has a contagious giggle. Elmo likes to be enthusiastic, friendly, and cheerful, and Elmo always wants to be part of everything that goes on. Sometimes Elmo doesn't know how to do what Elmo wants. But that never stops Elmo because Elmo has a very positive, optimistic view of Elmo and life!''',
            clips: ['vSYadh2xmcI','t0WELUxl7gc']),
          new Muppet(name:'Cookie Monster', gender: Muppet.MALE, color: "blue", hasFur: true, picture: 'cookie.jpg',
            about:'''All this talk of cookie make me hungry! Me a frenzied, but cuddly, monster. Me eat everything; chairs, microphones, numbers, letters, vegetables; me always on eternal quest to find more food (especially cookies)! Me like to solve problems, like what to eat when I'm hungry!''',
            clips: ['Ye8mB6VsUHw','kKW7TIjqZAA','KBMxpDbp51A']),
          new Muppet(name:'Zoe', gender: Muppet.FEMALE, color: "orange", hasFur: true, picture: 'zoe.jpg',
            about:'''I am known for talking at very high speeds and my beautiful ballet skills. I sometimes get so excited that my words cannot get out fast enough. I can also get jealous if I don't get enough attention. I like designing floating devices for my pet Rocco.''',
            clips: ['KkvCZLU1sGI']),
          new Muppet(name:'Grover', gender: Muppet.MALE, color: "blue", hasFur: true, picture: 'grover.jpg',
            about:'''I am a cute and furry blue monster. That is me. I am excitable, caring and compulsive'a combination that sometimes gets me into trouble. I love to help people but because of my inexperience, I usually end up doing things the long way around. If I do not succeed at first, I always try and try again. I have many jobs on Sesame Street but my favorite is working in Charlie's Restaurant.''',
            clips: ['auqyJ1FisSY','sIucCUyZF0g']),
          new Muppet(name:'Count Von Count', gender: Muppet.MALE, color: "purple", hasFur: true, picture: 'count.jpg',
            about:'''I am 1,832,652 years old! I've been counting. Some say I bear a comical resemblance to Count Dracula, but that is where the similarity ends. I thirst for numbers, not necks. I LOVE to count anything and everything! I'm very dependable; you can always count on me.''',
            clips: ['5l7KbMVdN7E','ZIniljT5lJI']),
          new Muppet(name:'Abby Cadabby', gender: Muppet.FEMALE, color: "purple", hasFur: true, picture: 'count.jpg',
            about:'''I am an inquisitive 3-year-old fairy-in-training who has moved to Sesame Street from Fairyside, Queens. My mommy is the Fairy Godmother. I am still learning magic and tend to turn things into pumpkins with my training wand. I go to Flying Fairy School with my friends Blogg and Gonnigan. I love fairytales, they are a part of my family history. I love bubbles and exploring them with my friends Elmo, Chris and Alan. I love to practice magic and rhyming, but what I find truly amazing and magical is what I discover on Sesame Street!''',
            clips: ['cjRQ6VyG1Yw']),
          ]

        muppets.each{
          it.save()
        }
			}
    }

    def destroy = {
    }
}
~~~

~~~
conf/UrlMappings.groovy
~~~
class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

		"/"(controller:'muppetDiscovery', action:"/index")
		"500"(view:'/error')
	}
}
~~~

# Stage V: Run Webapp
xxii. Play with the database

# Stage V: Run Webapp
xxiii. Play with the webapp

# Extra ball: Spock test!
xxiv. Configure plugin and run (compile --refresh-depencies)

# Extra ball: Spock test!
xxv. Let’s write some specs samples for unit and integration

~~~
package sesamestreet

import static org.junit.Assert.*

import grails.test.mixin.*
import grails.test.mixin.support.*
import org.junit.*

import spock.lang.Specification

@TestFor(Muppet)
class MuppetUnitSpec extends Specification {

	def "Ernie validates OK"() {
		when:
			def ernie = new Muppet(name:'ernie', gender:Muppet.MALE, color:'orange')
		then:
			ernie.validate()
	}

	def "Bert fails on validate"() {
		when:
			def bert = new Muppet(name:'bert', gender:Muppet.MALE)
		then:
			!bert.validate()
	}
	
	def "validate several of them"() {
		expect:
			new Muppet([name: name, color: color, gender: gender]).validate() == result
		where:
			name   | gender   		  | color			  | result
			'ernie'| Muppet.MALE    | 'orange' 		| true
			'bert' | Muppet.MALE    | null		    | false
			'elmo' | Muppet.MALE    | 'red'		    | true
			'zoe'  | Muppet.FEMALE  | 'orange'	  | true
	  }
}
~~~

# Extra ball: Spock test!
xxvi. Let’s write some specs samples for unit and integration and run them (test-app :spock)

~~~
package sesamestreet

import static org.junit.Assert.*

import grails.test.mixin.*
import grails.test.mixin.support.*
import org.junit.*

import grails.test.spock.IntegrationSpec

class MuppetIntegrationSpec extends IntegrationSpec {

	def "test save several muppets"() {
		when:
			def muppet = new Muppet([name: name, color: color, gender: gender]).save()
		then:
			muppet!=null == result
		where:
			name   | gender   		  | color			  | result
			'ernie'| Muppet.MALE    | 'orange' 		| true
			'bert' | Muppet.MALE    | null		    | false
			'elmo' | Muppet.MALE    | 'red'		    | true
			'zoe'  | Muppet.FEMALE  | 'orange'	  | true
	  }
}
~~~