<html>
<head>
	<%@ include file="snippets/header.jsp"%>
	<title>BC Login</title>
 <!-- Bootstrap core CSS -->
    <link href="../../../../dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="signin.css" rel="stylesheet">
</head>
<body>

    

    <div class="container">

      <form action="ControllerServlet" method="POST" class="form-signin">
        <h2 class="form-signin-heading"><fmt:message key="please.log.in"/></h2>
        <label for="dni" class="sr-only"><fmt:message key="personal.id"/></label>
        <input name="dni" type="text" id="dni" class="form-control" value="46994355L" required autofocus>
        <label for="inputPassword" class="sr-only"><fmt:message key="password"/></label>
        <input name="pass" type="password" id="inputPassword" class="form-control" value="jupiter" required>
        <input style="display:none" name="sw" type="text" id="sw" class="form-control" value="login" required>
        <div class="checkbox">
          <label>
            <input type="checkbox" value="remember-me"> <fmt:message key="remember.me"/>
          </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit"><fmt:message key="login"/></button>
      </form>

    </div> <!-- /container -->
	 
	 <br>
	 
	 <%@ include file="snippets/footer.html"%>

</body>
</html>