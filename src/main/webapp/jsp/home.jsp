<%@ page import="net.therap.mealplanner.entity.User" %><%--
  Created by IntelliJ IDEA.
  User: bashir
  Date: 10/24/16
  Time: 4:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
</head>
<body>
<h1>This is Home.</h1>
<h2>Welcome <%=request.getSession().getAttribute("user")%></h2>
<p><a href="/logout">Logout</a></p>
</body>
</html>
