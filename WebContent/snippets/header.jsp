<%@ include file="init.jsp" %>

<nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">Banco Jones</a>
    </div>
    <ul class="nav navbar-nav">
      <li><a href="#"><fmt:message key="about"/></a></li>
      <li><a href="login.jsp"><fmt:message key="login"/></a></li>
      <li><a href="registry.jsp"><fmt:message key="signup"/></a></li>
      <li><a href="ProfileUpdate.jsp"><fmt:message key="update.profile"/></a></li>
      <li><a href="ListAccountsServlet?sw=getaccounts"><fmt:message key="Accounts"/></a></li> <!-- Envia a un redirectFilter -->
      <li class="dropdown">
        <a class="dropdown-toggle" data-toggle="dropdown" href="#"><fmt:message key="language"/>
        <span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a value="ca"> Català</a></li>
          <li><a value="es"> Castellano</a></li>
        </ul>
      </li>
      <li>
	      <form action="">
			<select id="language" name="language" onchange="submit()">
				<option value="ca" > Idioma</option>
				<option value="ca" ${language == 'ca' ? 'selected': '' } > Catala</option>
				<option value="es" ${language == 'es' ? 'selected': '' } > Castellano</option>
			</select>
		</form>
	  </li>
    </ul>
  </div>
</nav>