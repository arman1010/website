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
<%
    User user = (User) request.getSession().getAttribute("user");
    List<User> allUsers = (List<User>) request.getAttribute("allUsers");
    List<User> allFriend = (List<User>) request.getAttribute("allFriend");
    List<User> allRequest = (List<User>) request.getAttribute("allRequest");
%>
<div class="profil">
    <img src="/getImage?picName=<%=user.getPicUrl()%>" width="100"/>
    <h3><%=user.getName()%> <%=user.getSurname()%>
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
    <%
        for (User allUser : allUsers) {%>
    <tr>
        <td><%=allUser.getName()%>
        </td>
        <td><%=allUser.getSurname()%>
        </td>
        <td><img src="/getImage?picName=<%=allUser.getPicUrl()%>" width="100">
        </td>
        <td><a href="/sendRequest?toId=<%=allUser.getId()%>">Send</a></td>
    </tr>
    <%}%>
</table>

<hr>

<table>
    <tr>
        <td>Name</td>
        <td>Surname</td>
        <td>Picture</td>
        <td>Accept or Reject</td>
    </tr>
    <%
        for (User allRequests : allRequest) {%>
    <tr>
        <td><%=allRequests.getName()%>
        </td>
        <td><%=allRequests.getSurname()%>
        </td>
        <td><img src="/getImage?picName=<%=allRequests.getPicUrl()%>" width="100">
        </td>
        <td>
            <a href="/acceptOrReject?userId=<%=allRequests.getId()%>&action=accept">Accept</a>
            <a href="/acceptOrReject?userId=<%=allRequests.getId()%>&action=reject">Reject</a>
        </td>
    </tr>
    <%}%>
</table>

<hr>

<table>
    <tr>
        <td>Name</td>
        <td>Surname</td>
        <td>Picture</td>
        <td>Accept or Reject</td>
    </tr>
    <%
        for (User allFriends : allFriend) {%>
    <tr>
        <td><%=allFriends.getName()%>
        </td>
        <td><%=allFriends.getSurname()%>
        </td>
        <td><img src="/getImage?picName=<%=allFriends.getPicUrl()%>" width="100">
        </td>
        <td>
            <a href="/remove?userId=<%=allFriends.getId()%>">Delete</a>
        </td>
        <td><a href="/toMessagePage?toId=<%=allFriends.getId()%>">message</a></td>
    </tr>
    <%}%>
</table>
</body>
</html>
