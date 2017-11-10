<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style type="text/css">
        #centerLayer {
            width: 700px; /* Ширина слоя в пикселах */
            margin: 0 auto; /* Отступ слева и справа */
            background: #acff85; /* Цвет фона */
            padding: 10px; /* Поля вокруг текста */
            text-align: start; /* Выравнивание содержимого слоя по левому краю */
        }
    </style>
    <title>User with contacts min 10</title>
</head>
<body>
<div id="centerLayer">
<h1>Пользователи с количеством контактов < 10 </h1>

<c:forEach items="${users}" var="user" varStatus="varStatus">
<h4>
    <table>
        <td>
            <tr>${user.login}</tr>
        </td>
    </table>
</c:forEach>
    <a href="/analyticalInf" >Назад</a>
</h4>
</div>
</body>
</html>
