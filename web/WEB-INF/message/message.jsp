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
<%
    List<Message> messages = (List<Message>) request.getAttribute("allMessage");
    User user = (User) request.getAttribute("toUser");
%>
<%
    for (Message message : messages) {%>
<h2>From: <%=message.getFromId().getName()%> To: <%=message.getToId().getName()%>
</h2>
<p><%=message.getText()%>
</p>
<h5><%=message.getDate()%>
</h5>
<%}%>
<form action="/message?toId=<%=user.getId()%>" method="post">
    <input type="text" name="message">
    <input type="submit" value="send">
</form>
</body>
</html>
