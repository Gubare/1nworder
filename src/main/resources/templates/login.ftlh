<!DOCTYPE html>
<html>
<head>
    <title></title>
</head>
<body>
<h1></h1><hr>
<h4>Авторизация</h4>
<form action="/login" method="post">
    Email: <input type="text" name="username"><br><br>
    Password: <input type="password" name="password"><br><br>
<#--    <input type="hidden" name="_csrf" value="${_csrf.token}">-->
    <input type="submit" value="Вход"/>
</form>
<a href="/registration">Зарегистрироваться</a>
</body>
</html>