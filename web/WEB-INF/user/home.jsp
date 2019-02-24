<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="model.User" %><%--
  Created by IntelliJ IDEA.
  User: Vardan
  Date: 19.02.2019
  Time: 4:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>home </title>
</head>
<body>

<div class="profil">
    <img src="/getImage?picName=${user.picUrl}" width="100"/>
    <h3>${user.name} ${user.surname}
    </h3>
    <a href="/logout">Logout</a>
</div>

<table>
    <tr>
        <td>Name</td>
        <td>Surname</td>
        <td>Picture</td>
        <td>Action</td>
    </tr>

    <c:forEach var="allUser" items="${allUsers}">
        <tr>
            <td>${allUser.name}
            </td>
            <td>${allUser.surname}
            </td>
            <td><img src="/getImage?picName=${allUser.picUrl}" width="100">
            </td>
            <td><a href="/sendRequest?toId=${allUser.id}">Send</a></td>
        </tr>
    </c:forEach>
</table>

<hr>

<table>
    <tr>
        <td>Name</td>
        <td>Surname</td>
        <td>Picture</td>
        <td>Accept or Reject</td>
    </tr>
    <c:forEach var="allRequests" items="allRequest">
    <tr>
        <td>${allRequests.name}
        </td>
        <td>${allRequests.surname}
        </td>
        <td><img src="/getImage?picName=${allRequests.picUrl}" width="100">
        </td>
        <td>
            <a href="/acceptOrReject?userId=${allRequests.id}&action=accept">Accept</a>
            <a href="/acceptOrReject?userId=${allRequests.id}&action=reject">Reject</a>
        </td>
    </tr>
    </c:forEach>
</table>

<hr>

<table>
    <tr>
        <td>Name</td>
        <td>Surname</td>
        <td>Picture</td>
        <td>Accept or Reject</td>
    </tr>
    <c:forEach var="allFriends" items="allFriend">

    <tr>
        <td>${allFriends.name}
        </td>
        <td>${allFriends.surname}
        </td>
        <td><img src="/getImage?picName=${allFriends.picUrl}" width="100">
        </td>
        <td>
            <a href="/remove?userId=${allFriends.id}">Delete</a>
        </td>
        <td><a href="/toMessagePage?toId=${allFriends.id}">message</a></td>
    </tr>
    </c:forEach>
</table>
</body>
</html>
