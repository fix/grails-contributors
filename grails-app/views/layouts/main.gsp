<!doctype html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>Grails Contributors</title>
<link rel="shortcut icon"
	href="${resource(dir: 'images', file: 'favicon.ico')}"
	type="image/x-icon">
<link
	href='http://fonts.googleapis.com/css?family=Nothing+You+Could+Do&v2'
	rel='stylesheet' type='text/css'>
<link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}"
	type="text/css">
<g:layoutHead />
<g:javascript library="application" />

</head>
<body>
	<div id="wrapper">
		<div id="header">
			<a href="${createLink(controller:'home')}">
				<table>
					<tr>
						<td>
							<img id="logo" src="${resource(dir: 'images', file: 'logo.png')}" alt="Grails" />
						</td>
						<td>
							<span style="color: #000000;">Grails</span>Contributors
						</td>
					</tr>
				</table>
			</a>

		</div>
		<div id="content">
			<g:layoutBody />
		</div>
		<div id="footer">
			Licensed under the terms of the Apache License, Version 2.0. Source
			code available on <a
				href="https://github.com/bobbywarner/grails-contributors"
				target="_blank">GitHub</a>.
		</div>
	</div>
</body>
</html>