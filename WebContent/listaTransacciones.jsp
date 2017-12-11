<html>
<head>
	<%@ include file="snippets/header.jsp" %>
	<%@ include file="snippets/requestClient.jsp" %>
	<%@ include file="snippets/requestTransactions.jsp" %>
	<title>BC <fmt:message key="Transactions"/></title>
</head>
<body>

	<h3><fmt:message key="Transactions"/></h3>
	
	<% String iban = (String) session.getAttribute("iban"); %>
	<% int pageto;
	if (request.getParameter("page") == null) {
		pageto = 1;
	} else {
		pageto = Integer.parseInt(request.getParameter("page"));
	}
	%>
	<% int perpage;
	if (request.getParameter("perpage") == null) {
		perpage = 5;
	} else {
		perpage = Integer.parseInt(request.getParameter("perpage"));
	}
	%>
	
	<div class="paginationdiv">
		<ul class="pagination">
		  <li><a href="listaTransaccioness.jsp?page=1">1</a></li>
		  <li><a href="listaTransaccioness.jsp?page=2">2</a></li>
		  <li><a href="listaTransaccioness.jsp?page=3">3</a></li>
		  <li><a href="listaTransaccioness.jsp?page=4">4</a></li>
		  <li><a href="listaTransaccioness.jsp?page=5">5</a></li>
		</ul>
	</div>
	
	<ul class="list-group">
		
		<% List<Transaction> transactions_paginated = transactions.subList((pageto-1)*perpage, ((pageto-1)*perpage)+perpage); %>
	
		<% for (Transaction transaction : transactions_paginated) { %>
			<li class="list-group-item" style="border-collapse:collapse">
				<div class="row">
					<div id="id" class="col-md-3"><%= transaction.getId() %></div>
					<div id="origin" class="col-md-3"><%= transaction.getOrigin() %></div>
					<div id="destination" class="col-md-3"><%= transaction.getDestination() %></div>
					<div id="amount" class="col-md-3"><%= transaction.getAmount() %></div>
				</div>
			</li>
		<% } %>
			<li class="list-group-item" style="border-collapse:collapse">
			<div id="id" class="col-md-16"><h4><fmt:message key="new.transaction"/></h4></div>
			<form action="ControllerServlet" method="POST" style="border-collapse:collapse">
				<div class="row">
					<div class="col-md-2"><input type="text" class="form-control" style="display:none" id="origin" name="origin" value="<%= request.getParameter("iban") %>"></div>
					<div class="col-md-2"><fmt:message key="amount"/>  <input id="amount" name="amount" value="0.00"/></div>
					<div class="col-md-1"><fmt:message key="into.account"/></div>
					<div class="col-md-3"><input type="text" class="form-control col-md-2" id="destination" name="destination"/></div>
					<div class="col-md-2"><button type="submit" class="btn btn-default"><span class="glyphicon glyphicon-plus"></span></button></div>
					<input style="display:none" id="sw" name="sw" value="newtransaction"/>
				</div>
			</form>
		</li>
	</ul>
	
	<div class="paginationdiv">
		<ul class="pagination">
		  <li><a href="listaTransaccioness.jsp?page=1">1</a></li>
		  <li><a href="listaTransaccioness.jsp?page=2">2</a></li>
		  <li><a href="listaTransaccioness.jsp?page=3">3</a></li>
		  <li><a href="listaTransaccioness.jsp?page=4">4</a></li>
		  <li><a href="listaTransaccioness.jsp?page=5">5</a></li>
		</ul>
	</div>
	
	<%@ include file="snippets/footer.html"%>
	

</body>
</html>