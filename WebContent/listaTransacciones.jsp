<html>
<head>
	<%@ include file="snippets/header.jsp" %>
	<%@ include file="snippets/requestClient.jsp" %>
	<%@ include file="snippets/requestTransactions.jsp" %>
	<title>BC <fmt:message key="Transactions"/></title>
</head>
<body>

	<h3><fmt:message key="Transactions"/></h3>
	
	<ul class="list-group">
		<li class="list-group-item" style="border-collapse:collapse">
			<div id="id" class="col-md-4"><h4><fmt:message key="new.transaction"/></h4></div>
			<form action="TransactionsServlet" method="POST" style="border-collapse:collapse">
				<div class="row">
					<div class="col-md-2"><input type="text" class="form-control" id="origin" name="origin"></div>
					<div class="col-md-2"></div>
					<div class="col-md-2"><input id="amount" name="amount" placeholder="0.00"/></div>
					<div class="col-md-2"></div>
					<div class="col-md-2"><button type="submit" class="btn btn-default"><span class="glyphicon glyphicon-plus"></span></button></div>
					<input style="display:none" id="sw" name="sw" value="newtransaction"/>
				</div>
			</form>
		</li>
	</ul>
	<ul class="list-group">
		<% for (Transaction transaction : transactions) { %>
			<li class="list-group-item" style="border-collapse:collapse">
				<div class="row">
					<div id="id" class="col-md-4"><%= transaction.getId() %></div>
					<div id="origin" class="col-md-4"><%= transaction.getOrigin() %></div>
					<div id="destination" class="col-md-4"><%= transaction.getDestination() %></div>
					<div id="amount" class="col-md-4"><%= transaction.getAmount() %></div>
				</div>
			</li>
		<% } %>		
	</ul>
	
	<%@ include file="snippets/footer.html"%>
	

</body>
</html>