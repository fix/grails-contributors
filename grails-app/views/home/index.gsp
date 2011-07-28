<!doctype html>
<html>
	<head>
		<meta name="layout" content="main"/>
	</head>
	<body>
		<div id="leftCol">
		<h1>Grails-Core</h1>
		<g:link action="commits" params="[repository:'grails/grails-core']">Commits</g:link>
		<table>
			<tbody>
				<g:each var="c" in="${coreContributions}">
				<tr class="row${c.rank%2}">
					<td># ${c.rank}</td>
					<td>
						<a href="${createLink(controller: 'contributor', action: 'show', params: [login:c.contributor.login])}">
							${c.contributor.name}
						</a>
					</td>
					<td> ${c.total}</td>
				</tr>
				</g:each>
			</tbody>
		</table>
		</div>
		<div id="rightCol">
		<h1>Grails-Doc</h1>
		<g:link action="commits" params="[repository:'grails/grails-doc']">Commits</g:link>
		<table>
			<tbody>
				<g:each var="c" in="${docContributions}">
				<tr class="row${c.rank%2}">
					<td># ${c.rank}</td>
					<td>
						<a href="${createLink(controller: 'contributor', action: 'show', params: [login: c.contributor.login])}">
							${c.contributor.name}
						</a>
					</td>
					<td> ${c.total}</td>
				</tr>
				</g:each>
			</tbody>
		</table>
		</div>
	</body>
</html>
