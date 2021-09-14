<#ftl encoding="UTF-8"/>
<!DOCTYPE html>
<html lang="en">
<#import "spring.ftl" as spring/>
<head>
    <meta charset="UTF-8">
    <title>Войти</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/css/login.css"/>
    <link rel="shortcut icon" href="/img/music-folder.png" type="image/x-icon">
</head>
<body>

<div class="page">
    <div class="heading">
        <a id="register" class="active"><span>Войти</span></a>
    </div>
    <div class="form">
        <form class="login-form" id="form" action="/signIn" method="post">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
            <div class="input-field">
                <input type="email" id="email" name="email" placeholder="Эл. почта">
            </div>
            <div class="input-field">
                <input type="password" id="password" name="password" placeholder="Пароль">
            </div>
            <div class="remember">
                <input id="remember-me" name="remember-me" type="checkbox" class="remember-input">
                <label for="remember-me" class="remember-label">Запомнить меня</label>
            </div>
            <input type="submit" value="Войти" class="input-button">
        </form>
        <div class="forgot">
            <a>Забыли пароль?</a>
        </div>
        <div class="swap">
            <a href="/signUp">Нет аккаунта? Зарегистрируйтесь</a>
        </div>
    </div>
</div>
</body>
</html>
