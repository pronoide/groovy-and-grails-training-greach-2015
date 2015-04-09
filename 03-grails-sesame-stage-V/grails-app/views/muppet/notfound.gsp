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