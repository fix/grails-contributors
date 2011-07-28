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
	  	<td style="vertical-align: top;" width="300px"><span id="image"><img src="http://www.gravatar.com/avatar/${contributor.gravatarId}?s=200" /><br/><br/>${contributor.login}</span></td>
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
	  		
	  			<h1 style="font-size: 30px; text-align:right;">${bestContribution.repository.split("/")[1]} #${bestContribution.rank} <span id="fieldname">(${bestContribution.total})</span></h1>
	  		
	  		</td>
	  		
	  </tr>
	  <tr><td colspan="2">
	  <br/>
	  <br/>
	  <h2>All Contributions</h2>
	  <g:each var="c" in="${contributor.contributions}">
	  		<p><a href="${createLink(controller:'home', action:'repository', params:[name:c.repository])}">${c.repository} #${c.rank}</a> <span id="fieldname">(${c.total})</span></p>
	  </g:each>
	  </td></tr>
  </table>
  
  </div>
</body>
</html>