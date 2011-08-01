<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<meta name="layout" content="main"/>
<title>Repository ${params.name}</title>
</head>
<body>
  <div class="body">
  <g:if test="${!contributions}">
  		<g:form action="repository">
  		<h1>Repository <g:textField style="background-color:#aaffaa; width:500px;" name="name"/></h1>
  		</g:form>
  </g:if>
  <g:else>
     <div>
		<h1>${params.name.split("/")[1].toUpperCase()}</h1>
		<h1><g:link action="commits" params="[repository:params.name]">Commits</g:link> | <a href="http://github.com/${params.name}">Source</a></h1>
		<br/>
		<table id="fullCol">
			<tbody>
				<g:each var="c" in="${contributions}">
				<tr class="row${c.rank%2}"><td>#${c.rank}</td><td><a target="_blank" href="${createLink(controller: 'contributor', action:'show', params:[login:c.contributor?.login])}">${c.contributor?.name}</a></td><td> ${c.total}</td></tr>
				</g:each>
			</tbody>
		</table>
		</div>
  </g:else>
  </div>
</body>
</html>