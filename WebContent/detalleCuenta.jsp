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
			<li class="list-group-item" style="border-collapse:collapse">
				<form action="ListAccountsServlet" method="POST" style="border-collapse:collapse">
					<div class="row">
						<div id="iban" name="iban" class="col-md-4"><%= account.getIban() %></div>
						<div class="col-md-4"><%= account.getSaldo() %> euros</div>
						<div class="col-md-2"><button type="submit" class="btn btn-default"><span class="glyphicon glyphicon-minus"></span></button></div>
						<input style="display:none" id="sw" name="sw" value="deleteaccount"/>
						<input style="display:none" id="iban" name="iban" value="<%= account.getIban() %>"/>
					</div>
				</form>
			</li>
		<% } %>
		<li class="list-group-item" style="border-collapse:collapse">
				<form action="ListAccountsServlet" method="POST" style="border-collapse:collapse">
					<div class="row">
						<div id="iban" name="iban" class="col-md-3"><input type="text" class="form-control" id="iban" name="iban" placeholder="<fmt:message key="iban24"/>"></div>
						<div class="col-md-1"></div>
						<div class="col-md-2">--</div>
						<div class="col-md-2"><fmt:message key="add.an.account"/></div>
						<div class="col-md-2"><button type="submit" class="btn btn-default"><span class="glyphicon glyphicon-plus"></span></button></div>
						<input style="display:none" id="sw" name="sw" value="insertaccount"/>
					</div>
				</form>
			</li>
	</ul>
	
	<%@ include file="snippets/footer.html"%>

</body>
</html>