<!doctype html>
<%@page import="org.grails.contributors.Contributor"%>
<html>
	<head>
		<meta name="layout" content="main"/>
	</head>
	<body>
        <h1 class="center">${params.repository}</h1>
        <br/>
        <table id="fullCol" cellpadding="10">
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
                    	
                        <td><a href="https://github.com/grails/${c.repository}/commit/${c.commitId}" target="_blank">${c.commitId.toString()[0..4]}..</a></td>
                        <td><a href="${createLink(action:'contributor',params:[login:c.contributor.login])}">${c.contributor.name}</a></td>
                        <td><g:formatDate date="${c.dateCreated}" format="yyyy-MM-dd" /></td>
                        <td>${c.message}</td>
                    </tr>
                </g:each>
            </tbody>
        </table>
	</body>
</html>
