<!doctype html>
<html>
	<head>
		<meta name="layout" content="main"/>
	</head>
	<body>
		<div id="fullCol" style="width:auto;">
		<h1><g:link controller="home" action="repositories">Check All Repositories</g:link></h1>
		<br/>
		</div>
		<br/>
		<div id="leftCol">
		<h1>Grails-Core <g:link action="commits" params="[repository:'grails/grails-core']">Commits</g:link> | <a href="http://github.com/grails/grails-core">Source</a></h1>
		<br/>
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
		<h1>Grails-doc <g:link action="commits" params="[repository:'grails/grails-doc']">Commits</g:link> | <a href="http://github.com/grails/grails-doc">Source</a></h1>
		<br/>
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
