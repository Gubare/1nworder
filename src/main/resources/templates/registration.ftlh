<!DOCTYPE html>
<html>
<head>
    <title></title>
    <meta name="viewport" content="initial-scale=1" />
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css">
</head>
<body style="background: rgb(9,213,255);
background: linear-gradient(38deg, rgba(9,213,255,1) 5%, rgba(242,226,244,1) 56%, rgba(229,154,238,1) 100%);
background-attachment:fixed;
height:100%;" >
<h1></h1><hr>
<form class="text-center" action="/registration" method="post">
<#--    Username:<input type="text" name="username"><br><br>-->
    <h4>Регистрация</h4>

    Почта: <input type="text" name="email"><br><br>
    Пароль: <input type="password" name="password"><br><br>
<#--    <input type="hidden" name="_csrf" value="${_csrf.token}">-->
    <input type="submit" value="Зарегистрироваться"/>
</form>

</body>
</html>