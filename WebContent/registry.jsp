<html>
<head>
	<%@ include file="snippets/header.jsp"%>
	<title>BC Sign Up</title>

</head>
<body>
	
	<div class="container">
  <h2><fmt:message key="new.profile"/></h2>
  <form action="RegistryServlet" method="POST">
    <div class="form-group">
      <label for="dni"><fmt:message key="personal.id"/></label>
      <input type="text" class="form-control" id="dni" name="dni" value="46994355L">
    </div>
    <div class="form-group">
      <label for="name"><fmt:message key="name"/></label>
      <input type="text" class="form-control" id="name" name="name" value="Albert">
    </div>
    <div class="form-group">
      <label for="surnames"><fmt:message key="surnames"/></label>
      <input type="text" class="form-control" id="surnames" name="surnames" value="Balbastre Morte">
    </div>
    <div class="form-group">
      <label for="pass"><fmt:message key="password"/></label>
      <input type="password" class="form-control" id="pass" name="pass" value="jupiter">
    </div>
    <div class="form-group">
      <label for="dob"><fmt:message key="date.of.birth"/></label>
      <input type="text" class="form-control" id="dob" placeholder="DD/MM/AAAA" name="dob" value="08/12/1988">
    </div>
    <div class="form-group">
      <label for="address"><fmt:message key="address"/></label>
      <input type="text" class="form-control" id="address" name="address" value="C/ Numancia, Barcelona">
    </div>
    <div class="form-group">
      <label for="sex"><fmt:message key="sex"/></label>
      <input type="text" class="form-control" id="sex" placeholder="h/m" name="sex" value="h">
    </div>
    <div class="form-group">
      <label for="phone"><fmt:message key="phone.number"/></label>
      <input type="text" class="form-control" id="phone" name="phone" value="601241674">
    </div>
    <button type="submit" class="btn btn-default"><fmt:message key="create.profile"/></button>
  </form>
</div>
	 
	 <br>
	<%@ include file="snippets/footer.html"%>
</body>
</html>