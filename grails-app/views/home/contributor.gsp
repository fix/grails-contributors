<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<meta name="layout" content="main"/>
<title>Contributor</title>
</head>
<body>
  <div class="body">
  <table id="rounded-corner">
	  <tr>
	  	<td width="300px"><span id="image"><img src="http://www.gravatar.com/avatar/${contributor.gravatarId}?s=200" /><br/><br/>${contributor.login}</span></td>
	  	<td style="text-align: left;vertical-align: bottom; font-size: 18px;">
	  		<span id="fieldname">Login </span>${contributor.login}
	  		<br/>
	  		<span id="fieldname">Name </span>${contributor.name}
	  		<br/>
	  		<span id="fieldname">Company </span>${contributor.company}
	  		<br/>
	  		<span id="fieldname">Location </span>${contributor.location}
	  		<br/>
	  		<span id="fieldname">Blog </span><a href="${contributor.blog}">${contributor.blog}</a>
	  		<br/>
	  		<g:each var="c" in="${contributor.contributions}">
	  			<h1 style="font-size: 34px; text-align:right;">${c.repository} #${c.rank} <span id="fieldname">(${c.total})</span></h1>
	  		</g:each>
	  		</td>
	  		
	  </tr>
  </table>
  
  </div>
</body>
</html>