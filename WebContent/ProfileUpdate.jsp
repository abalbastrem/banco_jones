<%@ include file="snippets/header.jsp" %>
<%@ include file="snippets/requestClient.jsp" %>

<html>
<head>
<%-- 	<%@ include file="snippets/header.jsp" %> --%>
<%-- 	<%@ include file="snippets/requestClient.jsp" %> --%>
	<title>BC Profile Update</title>
</head>
<body>
<%@ page import="beans.Cliente" %>


	
	
	
	
<div class="container">
  <h2><fmt:message key="update.profile"/></h2>
  <h3><%= c.getDni() %></h3>
  <form action="UpdateServlet" method="POST">
    <div class="form-group">
      <label for="name"><fmt:message key="name"/></label>
      <input type="text" class="form-control" id="name" name="name" value="<%= c.getName() %>">
    </div>
    <div class="form-group">
      <label for="surnames"><fmt:message key="surnames"/></label>
      <input type="text" class="form-control" id="surnames" name="surnames" value="<%= c.getSurnames() %>">
    </div>
    <div class="form-group">
      <label for="dob"><fmt:message key="date.of.birth"/></label>
      <input type="text" class="form-control" id="dob" placeholder="DD/MM/AAAA" name="dob" value="<%= c.getDob() %>">
    </div>
    <div class="form-group">
      <label for="address"><fmt:message key="address"/></label>
      <input type="text" class="form-control" id="address" name="address" value="<%= c.getAddress() %>">
    </div>
    <div class="form-group">
      <label for="sex"><fmt:message key="sex"/></label>
      <input type="text" class="form-control" id="sex" placeholder="h/m" name="sex" value="<%= c.getSex() %>">
    </div>
    <div class="form-group">
      <label for="telf"><fmt:message key="phone.number"/></label>
      <input type="text" class="form-control" id="telf" name="telf" value="<%= c.getPhone() %>">
    </div>
    <button type="submit" class="btn btn-default"><fmt:message key="update.profile"/></button>
  </form>
</div>
	 
	 <br>
	 
	 <%@ include file="snippets/footer.html"%>

</body>
</html>