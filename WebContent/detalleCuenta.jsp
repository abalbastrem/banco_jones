<html>
<head>
	<%@ include file="snippets/header.jsp"%>
	<%@ include file="snippets/requestClient.jsp" %>
	<%@ include file="snippets/requestAccounts.jsp"%>
	<title>BC Home</title>
</head>
<body>	
	
	<h3><fmt:message key="Accounts"/></h3>
	<ul class="list-group">
	<% for (Account account : accounts) { %>
		<li class="list-group-item"><div><%= account.getIban() %></div><div><%= account.getSaldo() %> euros</div></li>
	<% } %>
		<li class="list-group-item"><fmt:message key="add.an.account"/>
  			<form action="ListAccountsServlet" method="POST">
		    <div class="form-group">
		      <input type="text" class="form-control" id="sw" name="sw" value="insertaccount" style="display:none">
		      <input type="text" class="form-control" id="iban" name="iban" placeholder="<fmt:message key="iban24"/>">
		      <button type="submit" class="btn btn-default" value="+"><span class="glyphicon glyphicon-plus"></span></button>
		    </div>
		</li>
	</ul>
	
	<%@ include file="snippets/footer.html"%>

</body>
</html>