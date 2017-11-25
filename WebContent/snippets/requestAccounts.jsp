<%@ page import="beans.Account" %>

<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>

<%
List<Account> accounts = (List<Account>) session.getAttribute("accounts");
%>