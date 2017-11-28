<%@ page import="beans.Transaction" %>

<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>

<%
List<Transaction> transactions = (List<Transaction>) session.getAttribute("transactions");
%>