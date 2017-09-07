<%@ page language="java" pageEncoding="UTF-8" session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Справочник контактов</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="<c:url value="/bootstrap/css/bootstrap.min.css"/>">
    <script src="<c:url value="/bootstrap/js/jquery.min.js"/>"></script>
    <script src="<c:url value="/bootstrap/js/bootstrap.min.js"/>"></script>
    <link rel="stylesheet" href="<c:url value="/bootstrap/css/style.css"/>">
</head>
<body>

<div class="container">
    <h2>
        <div style="display: inline-block;">
            Справочник контактов
        </div>
        <div style="width: 100%; display: inline-block; text-align: right;">
            <a href="<c:url value="/startServlet"/>" class="btn btn-default" role="button">Выйти</a>
        </div>
    </h2>
    <form data-toggle="validator" role="form" action="<c:url value="/addUser"/>" method="post" style="width: 50%;">
        <div class="form-group">
            <label>Логин:</label>
            <input type="text" class="form-control" name="username" placeholder="Логин" required>
        </div>
        <div class="form-group">
            <label>Password:</label>
            <input type="password" class="form-control" name="password" placeholder="Enter password" required>
        </div>
        <div class="form-group">
            <ul class="list-group">
                <li class="list-group-item">
                    Активный
                    <div class="material-switch pull-right">
                        <input id="enabled" name="enabled" type="checkbox"/>
                        <label for="enabled" class="label-default"></label>
                    </div>
                </li>
            </ul>
        </div>
        <span style="width: 100%; display: inline-block;">
            <button type="submit" class="btn btn-default">Сохранить</button>
            <a href="<c:url value="/selection"/>" class="btn btn-default" role="button">Назад</a>
        </span>
    </form>
</div>
</body>
</html>
