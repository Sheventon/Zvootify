<#ftl encoding='UTF-8'>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Admin</title>
    <link rel="stylesheet" href="/css/header.css">
    <link rel="stylesheet" href="/css/admin.css">
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>
    <script src="/js/account.js"></script>
    <script src="/js/header.js"></script>
    <script src="/js/admin.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="/img/music-folder.png" type="image/x-icon">
</head>
<body>
<section>
    <h1>Пользователи</h1>
    <div class="tbl-header">
        <table cellpadding="0" cellspacing="0" border="0">
            <thead>
            <tr>
                <th>Id</th>
                <th>Имя</th>
                <th>Фамилия</th>
                <th>Email</th>
                <th>Peso</th>
                <th>Роль</th>
                <th>Статус</th>
                <th>Удален</th>
                <th>BAN</th>
            </tr>
            </thead>
        </table>
    </div>
    <div class="tbl-content">
        <table cellpadding="0" cellspacing="0" border="0">
            <tbody>
            <#list users as user>
                <#if user.userRole.name() == "USER">
                <tr>
                    <td>${user.id}</td>
                    <td>${user.firstName}</td>
                    <td>${user.lastName}</td>
                    <td>${user.email}</td>
                    <td>${user.peso}</td>
                    <td>${user.userRole}</td>
                    <td>${user.userState}</td>
                    <#if user.isDeleted??>
                        <td>Да</td>
                    <#else>
                        <td>Нет</td>
                    </#if>
                    <td>
                        <form method="post" action="/ban/${user.id}">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                            <#if user.userState.name() == "BANNED">
                                <input type="submit" value="Разбанить" class="ban-button">
                            <#else>
                                <input type="submit" value="Забанить" class="ban-button">
                            </#if>
                        </form>
                    </td>
                </tr>
                </#if>
            </#list>
            </tbody>
        </table>
    </div>
</section>
</body>
</html>
