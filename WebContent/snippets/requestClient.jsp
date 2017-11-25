<%@ page import="beans.Cliente" %>

<%
Cliente c = null;
String user = null;
String dni = null;
String name = null;
String surnames = null;
String pass = null;
String dob = null;
String address = null;
String sex = null;
String phone = null;
String sessionID = null;

if (session.getAttribute("clientSession") == null ) {
	System.out.print("CLIENTSESSION NULL");
	response.sendRedirect("../banco_jones/login.jsp");
	return;
} else {
	c = (Cliente) session.getAttribute("clientSession");
}

Cookie[] cookies = request.getCookies();
if ( cookies != null ) {
	for ( Cookie cookie : cookies ) {
		if (cookie.getName().equals("JSESSIONID")) sessionID = cookie.getValue();
	}
} else {
	sessionID = "No hay cookies";
}

%>