<!DOCTYPE html>
<html>
<head>
    <title></title>
</head>
<body>
<h1></h1><hr>
<h4>Регистрация</h4>
<form action="/registration" method="post">
    Username:<input type="text" name="username"><br><br>
    Email: <input type="text" name="email"><br><br>
    Password: <input type="password" name="password"><br><br>
<#--    <input type="hidden" name="_csrf" value="${_csrf.token}">-->
    <input type="submit" value="Вход"/>
</form>

</body>
</html>