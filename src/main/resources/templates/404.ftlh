<#ftl encoding="UTF-8"/>
<!DOCTYPE html>
<html lang="en">
<#import "spring.ftl" as spring/>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="/css/404.css"/>
    <link rel="shortcut icon" href="/img/music-folder.png" type="image/x-icon">
    <title>Not Found</title>
</head>
<body>
<div class="error-page">
    <div>
        <!--h1(data-h1='400') 400-->
        <!--p(data-p='BAD REQUEST') BAD REQUEST-->
        <!--h1(data-h1='401') 401-->
        <!--p(data-p='UNAUTHORIZED') UNAUTHORIZED-->
        <!--h1(data-h1='403') 403-->
        <!--p(data-p='FORBIDDEN') FORBIDDEN-->
        <h1 data-h1=""></h1>
        <p data-p="NOT FOUND">SOMETHING WENT WRONG</p>
        <!--h1(data-h1='500') 500-->
        <!--p(data-p='SERVER ERROR') SERVER ERROR-->
    </div>
</div>
<div id="particles-js"></div>
<a href="/account" class="back">GO TO ACCOUNT</a>
<script src="/js/404.js"></script>
<script src="https://cdn.rawgit.com/progers/pathseg/master/pathseg.js"></script>
</body>
</html>
