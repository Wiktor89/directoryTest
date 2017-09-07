<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User with contacts min 10</title>
</head>
<body>
<h1>Пользователи с количеством контактов < 10 </h1>

<c:forEach items="${users}" var="user" varStatus="varStatus">
<h4>
    <table>
        <td>
            <tr>${user}</tr>
        </td>
    </table>
</c:forEach>

    <a href="/analyticalInf" >Назад</a>
</h4>
</body>
</html>
