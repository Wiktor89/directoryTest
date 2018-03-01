<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
           uri="http://www.springframework.org/security/tags"%>
<%@ page session="false"%>
<html>
<head>
    <style type="text/css">
        #centerLayer {
            width: 400px; /* Ширина слоя в пикселах */
            margin: 0 auto; /* Отступ слева и справа */
            background: #acff85; /* Цвет фона */
            padding: 10px; /* Поля вокруг текста */
            text-align: start; /* Выравнивание содержимого слоя по левому краю */
        }
    </style>
</head>
<body>
<h3>
<div id="centerLayer">
<a href="<c:url value="/j_spring_security_logout"/>">Logout</a>
<a href="<c:url value="/startServlet"/>">Выйти</a>
</div>
</h3>
</body>
</html>
