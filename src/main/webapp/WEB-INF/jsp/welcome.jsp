<%--
  Created by IntelliJ IDEA.
  User: Kate
  Date: 27.02.2023
  Time: 13:56
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome!</title>
</head>
<body>
${userName}

<div>
    <h1>System Users</h1>
</div>
<div>
    <table>
        <tr>
            <td>User Id</td>
            <td>User Name</td>
            <td>User Surname</td>
            <td>Gender</td>
            <td>Address Id</td>
            <td>E-mail</td>
            <td>Phone</td>
            <td>Login</td>
            <td>Password</td>
            <td>User Ip</td>
            <td>Hash</td>
            <td>Created</td>
            <td>Changed</td>
            <td>Edit</td>
            <td>Delete</td>
        </tr>
        <c:forEach var="user" items="${users}">
            <tr>
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td>${user.surname}</td>
                <td>${user.gender}</td>
                <td>${user.addressId}</td>
                <td>${user.eMail}</td>
                <td>${user.phone}</td>
                <td>${user.login}</td>
                <td>${user.password}</td>
                <td>${user.userIp}</td>
                <td>${user.hash}</td>
                <td>${user.created}</td>
                <td>${user.change}</td>
                <td>
                    <button>Edit</button>
                </td>
                <td>
                    <button>Delete</button>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>


</body>

</html>
