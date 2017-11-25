<html>
<head>
	<%@ include file="snippets/header.jsp"%>
	<%@ include file="snippets/requestClient.jsp" %>
	<title>BC Home</title>
</head>
<body>	
	
	<h3><fmt:message key="welcome"/> <%= c.getName() %>, <fmt:message key="with"/> dni <%= c.getDni() %> <fmt:message key="and.session.id"/> <%= sessionID %></h3>
	
	
	<%@ include file="snippets/footer.html"%>

</body>
</html>