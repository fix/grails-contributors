<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<meta name="layout" content="main"/>
<title>Repositories</title>
</head>
<body>
	<div>
		<table id="fullCol" cellpadding="10" style="font-size: 26px;">
			<thead>
				<tr>
					<th>Repository</th><th>Commits</th><th>Main Contributor (#commits)</th>
				</tr>
			</thead>
			<tbody>
			<g:each in="${contributions}" var="repo" status="ctr">
				<tr class="row${ctr%2}">
					<td><a href="${createLink(controller:'home', action:'repository', params:[name:repo.key])}">${repo.key.split("/")[1]}</a></td>
					<td><a href="${createLink(controller:'home', action:'commits', params:[repository:repo.key])}">${repo.value.total}</a></td>
					<td><img src="http://www.gravatar.com/avatar/${repo.value.contributor.gravatarId}?s=40"/> <a href="${createLink(controller:'contributor', action:'show', params:[login:repo.value.contributor.login])}">${repo.value.contributor.name}</a> (${repo.value.max})</td>
	  			</tr>
	  		</g:each>
	  		</tbody>
  		</table>
	</div>
</body>
</html>