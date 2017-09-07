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
            <a style="text-decoration: none; color:inherit;" href="<c:url value="/users.do"/>">Список пользователей</a>
        </div>
        <div style="width: 100%; display: inline-block; text-align: right;">
            <a href="<c:url value="/selection"/>" class="btn btn-default" role="button">Назад</a>
        </div>
    </h2>
    <form role="form" action="<c:url value="/listUsers"/>" method="get">
        <div class="form-group">
            <div style="display: inline-block;">
                <input type="text" class="form-control" name="key" placeholder="Поиск:">
            </div>
            <div style="display: inline-block;">
                <button type="submit" class="btn btn-default">Искать</button>
            </div>
        </div>
    </form>
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
                <td><a style="text-decoration: none; color:inherit;" href="<c:url value="/user.do?id=${user.id}"/>"><c:out value="${user.login}"/></a></td>
                <td><c:out value="${user.password}"/></td>
                <td><c:out value="${user.role.title}"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <p>
        <span style="width: 100%; text-align: right; display: inline-block;">
            <a href="<c:url value="/users/add.do"/>" class="btn btn-default" role="button">Добавить пользователя</a>&nbsp;
            <a href="<c:url value="/roles.do"/>" class="btn btn-default" role="button">Добавить роль</a>
        </span>
    </p>
</div>
</body>
</html>