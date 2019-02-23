<%--
  Created by IntelliJ IDEA.
  User: Vardan
  Date: 19.02.2019
  Time: 4:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>register</title>
</head>
<body>
<form action="/register" method="post" enctype="multipart/form-data">
    Name: <input type="text" name="name"><br>
    Surname: <input type="text" name="surname"><br>
    Email: <input type="text" name="email"><br>
    Password: <input type="password" name="password"><br>
    Image: <input type="file" name="picture">
    <input type="submit" value="Register">
</form>
</body>
</html>
