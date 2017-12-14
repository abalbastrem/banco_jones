// NO SE USA PARA LA PRÁCTICA //
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>M06 Proyecto 01</title>
</head>
<body>
	<form action="ValidaDades">
		 
		Nombre completo: <input type="text" name="nombre">
		<p>${message_nombre}</p>
		<br> Fecha de nacimiento (formato DD/MM/AAAA): <input type="text"
			name="fecha"><br> DNI: <input type="text" name="dni"><br>
		letra: <input type="text" name="letra"><br> <input
			type="submit" value="submit">
		email: <input type="text" name="email"><br> <input
			type="submit" value="submit">
	</form>
	
	<p>${message}</p>
	 
	 
	<%= new java.util.Date().toString() %>
	<%= "ejemplo".toUpperCase() %>
	<%= (8*2) %>

	<!--  Declaraciones -->
	<%! private int count = 0; %>
	<%= ++count %>

	<!--  scriptlets -->
	<% if (count == 0){ %>
	hola
	<% }else{ %>
	adios
	<%} %>

	<%@ include file="snippets/footer.html"%>
	
	
<%--
<!-- En comptes d'enviar una cookie, posem la sesió ID per url. Això pot ser útil en cas que algú hagi deshabilitat les cookies del navegador -->
<form action="<%response.encodeURL("PerfilServlet"); %>" method="post">
	<input type="submit" value="Perfil">
</form>
<a href="<%response.encodeURL("LogoutServlet"); %>"></a>
<a href="LogoutServlet">Logout</a>
--%>
</body>
</html>