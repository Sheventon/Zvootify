<#ftl encoding="UTF-8"/>
<!DOCTYPE html>
<html lang="en">
<#import "spring.ftl" as spring/>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <meta name="_csrf" content="${_csrf.token}"/>
    <link rel="stylesheet" href="/css/code_album.css">
    <link rel="stylesheet" href="/css/header.css">
    <link rel="shortcut icon" href="/img/music-folder.png" type="image/x-icon">
    <title>Album</title>
</head>
<body>
<header class="header">
    <div class="container">
        <div class="header-body">
            <div class="header-burger">
                <span></span>
            </div>
            <nav class="header-menu">
                <ul class="header-list">
                    <li><a href="/account" class="header-link">Моя страница</a></li>
                    <li><a href="/review" class="header-link">Обзор</a></li>
                    <li><a href="/favorite" class="header-link">Избранное</a></li>
                    <li><a href="/logout" class="header-link">Выйти</a></li>
                </ul>
            </nav>
        </div>
    </div>
</header>
<div class="background" style="background: url('${album.pathToCover}') center; background-size: cover;"></div>
<section>
    <div class="album-info">
        <div class="album-art">
            <div class="actions">
                <#assign haveAlbum = 0>
                <#if user.albums[0]??>
                    <#list user.albums as userAlbum>
                        <#if userAlbum.id == album.id>
                            <div class="play">Добавлено</div>
                            <#assign haveAlbum = 1>
                            <#break>
                        </#if>
                    </#list>
                </#if>
                <#if haveAlbum == 0>
                    <form action="/buy-album/${album.id}" method="post">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                        <input type="submit" class="play" value="Добавить">
                    </form>
                    <div class="money">
                        <p class="head peso-count">
                            <#assign count = 0>
                            <#list album.songs as albumSong>
                                <#list user.songs as userSong>
                                    <#if albumSong.id == userSong.id>
                                        <#assign count += 1>
                                    </#if>
                                </#list>
                            </#list>
                            ${album.cost - count}
                        </p>
                        <p class="head peso"></p>
                    </div>
                </#if>
            </div>
        </div>
        <div class="album-details">
            <h2><#list album.singer as singer><#if singer_has_next>${singer.name + ", "}<#else>${singer.name}</#if></#list></h2>
            <h2 class="album-name">${album.name}</h2>
            <h5>${album.yearOfIssue}</h5>
        </div>
    </div>
    <div class="album-tracks">
        <ol>
            <#list album.songs as song>
                <li>
                    <span>${song.name}</span>
                    <div class="money-song">
                        <p class="peso-count-song">${song.cost}</p>
                        <p class="peso-song"></p>
                        <#assign i = 0>
                        <#list user.songs as userSong>
                            <#if userSong.id == song.id>
                                <#assign i = 1>
                            </#if>
                        </#list>
                        <#if i == 1>
                            <div class="plus" id="buy-song ${song.id}" style="visibility: hidden;"></div>
                        <#else>
                            <div class="plus" id="buy-song ${song.id}" onclick="buy(${song.id})"></div>
                        </#if>
                    </div>
                </li>
            </#list>
        </ol>
    </div>
</section>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script src="/js/header.js"></script>
<script src="/js/buy_song_ajax.js"></script>
</body>
</html>
