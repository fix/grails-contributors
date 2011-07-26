<!doctype html>
<html>
	<head>
		<meta name="layout" content="main"/>
	</head>
	<body>
        <h1 class="center">All Commits</h1>
        <table>
            <thead>
                <tr>
                    <th>id</th>
                    <th>contributor</th>
                    <th>date</th>
                    <th>message</th>
                </tr>
            </thead>
            <tbody>
                <g:each var="c" in="${commits}" status="ctr">
                    <tr class="row${ctr%2}">
                        <td>${c.commitId}</td>
                        <td><a href="${createLink(action:'contributor', params:[login:c.contributor.login])}">${c.contributor.name? c.contributor.name:c.contributor.login}</a></td>
                        <td><g:formatDate date="${c.dateCreated}" format="yyyy-MM-dd" /></td>
                        <td>${c.message}</td>
                    </tr>
                </g:each>
            </tbody>
        </table>
	</body>
</html>
