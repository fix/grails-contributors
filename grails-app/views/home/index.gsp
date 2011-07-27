<!doctype html>
<html>
	<head>
		<meta name="layout" content="main"/>
	</head>
	<body>
		<div id="leftCol">
		<h1>Grails-Core</h1>
		<g:link action="commits" params="[repository:'grails-core']">Commits</g:link>
		<table>
			<tbody>
				<g:each var="c" in="${coreContributions}">
				<tr><td># ${c.rank}</td><td><a target="_blank" href="${createLink(action:'contributor',params:[login:c.contributor.login])}">${c.contributor.name}</a></td><td> ${c.total}</td></tr>
				</g:each>
			</tbody>
		</table>
		</div>
		<div id="rightCol">
		<h1>Grails-Doc</h1>
		<g:link action="commits" params="[repository:'grails-doc']">Commits</g:link>
		<table>
			<tbody>
				<g:each var="c" in="${docContributions}">
					<tr><td># ${c.rank}</td><td><a target="_blank" href="${createLink(action:'contributor',params:[login:c.contributor.login])}">${c.contributor.name}</a></td><td> ${c.total}</td></tr>
				</g:each>
			</tbody>
		</table>
		</div>
	</body>
</html>
