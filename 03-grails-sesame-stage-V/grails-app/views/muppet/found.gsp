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
