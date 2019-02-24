<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Message" %>
<%@ page import="model.User" %><%--
  Created by IntelliJ IDEA.
  User: Vardan
  Date: 21.02.2019
  Time: 22:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<c:forEach var="message" items="allMessage">
    <h2>From: ${message.fromId.name} To: ${message.toId.name}
    </h2>
    <p>${message.text}
    </p>
    <h5>${message.date}
    </h5>
</c:forEach>

<form action="/message?toId=${user.id}" method="post">
    <input type="text" name="message">
    <input type="submit" value="send">
</form>
</body>
</html>
