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