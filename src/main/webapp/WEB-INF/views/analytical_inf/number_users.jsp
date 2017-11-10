<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style type="text/css">
        #centerLayer {
            width: 600px; /* Ширина слоя в пикселах */
            margin: 0 auto; /* Отступ слева и справа */
            background: #acff85; /* Цвет фона */
            padding: 10px; /* Поля вокруг текста */
            text-align: start; /* Выравнивание содержимого слоя по левому краю */
        }
    </style>
    <title>Action</title>
</head>
<body>
<div id="centerLayer">
<form action="number_users.jsp" method="post">
<h1>Кол- во пользователей  ${number}</h1>
    <h4>
        <a href="/analyticalInf" >Назад</a>
    </h4>
</form>
</div>
</body>
</html>
