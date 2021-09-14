<#ftl encoding="UTF-8"/>
<!DOCTYPE html>
<html lang="en">
<#import "spring.ftl" as spring/>
<head>
    <meta charset="UTF-8">
    <title>Регистрация</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/css/login.css">
    <link rel="shortcut icon" href="/img/music-folder.png" type="image/x-icon">
</head>
<body>

<div class="page">
    <div class="heading">
        <a id="register" class="active"><span>Регстрация</span></a>
    </div>
    <div class="form">
        <@spring.bind "userSignUpForm"/>
        <#if namesErrorMessage??>
            <p class="error">${namesErrorMessage}</p>
        </#if>
        <form class="login-form" id="form" action="/signUp" method="post">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
            <div class="input-field">
                <#--<#assign firstnamePlaceholder>Имя</#assign>-->
                <@spring.formInput "userSignUpForm.firstName" "placeholder='Имя'"/>
                <@spring.showErrors "<br>" "error"/>
            </div>
            <div class="input-field">
                <#--<#assign lastnamePlaceholder>Фамилия</#assign>-->
                <@spring.formInput "userSignUpForm.lastName" "placeholder='Фамилия'"/>
                <@spring.showErrors "<br>" "error"/>
            </div>
            <div class="input-field">
                <#--<#assign emailPlaceholder>Эл. почта</#assign>-->
                <@spring.formInput "userSignUpForm.email" "placeholder='Эл. почта'"/>
                <@spring.showErrors "<br>" "error"/>
            </div>
            <div class="input-field">
                <#--<#assign passwordPlaceholder>Пароль (более 7 символов)</#assign>-->
                <@spring.formPasswordInput "userSignUpForm.password" "placeholder='Пароль (более 7 символов)'"/>
                <@spring.showErrors "<br>" "error"/>
            </div>
            <input type="submit" value="Зарегистрироваться" class="input-button">
        </form>
        <div class="forgot">
            <a>Забыли пароль?</a>
        </div>
        <div class="swap">
            <a href="/signIn">Уже есть аккаунт? Войдите</a>
        </div>
    </div>
</div>
</body>
</html>
