<%@ page language="java" pageEncoding="UTF-8" session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Список пользователей</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="<c:url value="/bootstrap/css/bootstrap.min.css"/>">
    <script src="<c:url value="/bootstrap/js/jquery.min.js"/>"></script>
    <script src="<c:url value="/bootstrap/js/bootstrap.min.js"/>"></script>
</head>
<body>

<div class="container">
    <h2>
        <div style="display: inline-block;">
            <a style="text-decoration: none; color:inherit;" href="<c:url value="/listUsers"/>">Список пользователей</a>
        </div>
        <div style="width: 100%; display: inline-block; text-align: right;">
            <a href="<c:url value="/selection"/>" class="btn btn-default" role="button">Назад</a>
        </div>
    </h2>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>Логин</th>
            <th>password</th>
            <th>Роль</th>
            <th>&nbsp;</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${users}" var="user" varStatus="status">
            <tr valign="top">
            <tr>
            <tr>
                <td><a style="text-decoration: none; color:inherit;" href="<c:url value="/role.jsp?id=${user.id}"/>"><c:out value="${user.login}"/></a></td>
                <td><c:out value="КОНФИДЕНЦИАЛЬНО"/></td>
                <td><c:out value="${user.role.title}"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>