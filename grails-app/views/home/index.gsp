<!doctype html>
<html>
	<head>
		<meta name="layout" content="main"/>
	</head>
	<body>
		<div id="leftCol">
		<h1>Grails-Core</h1>
		<g:link action="commits">Commits</g:link>
		<table>
			<tbody>
				<g:each var="c" in="${coreContributors}">
				<tr><td># ${c.rank}</td><td><a target="_blank" href="${createLink(action:'contributor',params:[login:c.login])}">${c.login}</a></td><td> ${c.contributions}</td></tr>
				</g:each>
			</tbody>
		</table>
		</div>
		<div id="rightCol">
		<h1>Grails-Doc</h1>
		<table>
			<tbody>
				<g:each var="c" in="${docContributors}">
					<tr><td># ${c.rank}</td><td><a target="_blank" href="${createLink(action:'contributor',params:[login:c.login])}">${c.login}</a></td><td> ${c.contributions}</td></tr>
				</g:each>
			</tbody>
		</table>
		</div>
	</body>
</html>
