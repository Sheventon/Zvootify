<#ftl encoding="UTF-8"/>
<!DOCTYPE html>
<html lang="en">
<#import "spring.ftl" as spring/>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Account</title>
    <link rel="stylesheet" href="/css/header.css">
    <link rel="stylesheet" href="/css/player.css">
    <link rel="stylesheet" href="/css/account.css">
    <link rel="shortcut icon" href="/img/music-folder.png" type="image/x-icon">
</head>
<body>
<div class="app-container">
    <div class="app-header">
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

        <button class="messages-btn"></button>
    </div>
    <div class="app-content">
        <div class="projects-section">
            <div class="projects-section-header">
                <p>${user.firstName} ${user.lastName}</p>
                <div class="money">
                    <p class="peso"></p>
                    <p class="peso-count">
                        <#if user.peso <= 0>
                            0
                        <#else>
                            ${user.peso}
                        </#if>
                    </p>
                </div>
            </div>
            <div class="projects-section-line">
                <div class="projects-status">
                    <div class="item-status">
                        <span class="status-number">
                            <#if songs??>
                                ${songs?size}
                            <#else>
                                0
                            </#if>
                        </span>
                        <span class="status-type">Песни</span>
                    </div>
                    <div class="item-status">
                        <span class="status-number">
                            <#if albums??>
                                ${albums?size}
                            <#else>
                                0
                            </#if></span>
                        <span class="status-type">Альбомы</span>
                    </div>
                    <div class="item-status">
                        <span class="status-number">
                            <#if playlists??>
                                ${playlists?size}
                            <#else>
                                0
                            </#if>
                        </span>
                        <span class="status-type">Плейлисты</span>
                    </div>
                </div>
                <button class="mode-switch" title="Switch Theme">
                    <svg class="moon" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round"
                         stroke-width="2" width="24" height="24" viewBox="0 0 24 24">
                        <defs></defs>
                        <path d="M21 12.79A9 9 0 1111.21 3 7 7 0 0021 12.79z"></path>
                    </svg>
                </button>
            </div>
            <div class="albums">
                <div class="projects-section-header">
                    <p>Мои Альбомы</p>
                </div>
                <div class="project-boxes jsGridView">
                    <#list albums as album>
                        <a href="/album/${album.singer[0].name}/${album.name}" class="project-box-wrapper">
                            <div class="project-box" style="background: url('${album.pathToCover}');
                                    background-position: center center;
                                    background-repeat: no-repeat;
                                    background-size: cover;">
                                <div class="album-content">
                                    <div class="project-box-content-header">
                                        <p class="box-content-header">${album.name}</p>
                                        <#list album.singer as singer><p
                                                class="box-content-subheader">${singer.name}</p></#list>
                                    </div>
                                    <div class="project-box-footer">
                                        <p>${album.yearOfIssue}</p>
                                    </div>
                                </div>
                            </div>
                        </a>
                    </#list>
                </div>

                <div class="projects-section-header">
                    <p>Мои плейлисты</p>
                    <div class="add-playlist" id="plus" onclick="update_visibility('form')"></div>
                </div>

                <div class="project-boxes jsGridView">
                    <#list playlists as playlist>
                        <a href="/playlist/${playlist.name}/${user.id}" class="project-box-wrapper">
                            <#if playlist.songs[0]??>
                            <div class="project-box" style="background: url('${playlist.songs[0].album.pathToCover}');
                                    background-position: center center;
                                    background-repeat: no-repeat;
                                    background-size: cover;">
                                <#else>
                                <div class="project-box"
                                     style="background: #7FC6A6;
                                             background-position: center center;
                                             background-repeat: no-repeat;
                                             background-size: cover;">
                                    </#if>
                                    <div class="album-content">
                                        <div class="project-box-content-header">
                                            <p class="box-content-header">${playlist.name}</p>
                                            <p class="box-content-subheader">${user.firstName} ${user.lastName}</p>
                                        </div>
                                        <div class="project-box-footer">
                                            <p>Песен: ${playlist.songsCount}</p>
                                        </div>
                                    </div>
                                </div>
                        </a>
                    </#list>
                </div>
            </div>
        </div>
        <div class="messages-section">
            <button class="messages-close">
                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none"
                     stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"
                     class="feather feather-x-circle">
                    <circle cx="12" cy="12" r="10"/>
                    <line x1="15" y1="9" x2="9" y2="15"/>
                    <line x1="9" y1="9" x2="15" y2="15"/>
                </svg>
            </button>
            <div class="projects-section-header">
                <p>Мои Песни</p>
            </div>
            <div class="messages">

                <div class="player">
                    <div class="player__header">
                        <div class="player__img player__img--absolute slider">
                            <button class="player__button player__button--absolute--nw playlist">
                                <img src="http://physical-authority.surge.sh/imgs/icon/playlist.svg"
                                     alt="playlist-icon">
                            </button>
                            <button class="player__button player__button--absolute--center play">
                                <img src="http://physical-authority.surge.sh/imgs/icon/play.svg" alt="play-icon">
                                <img src="http://physical-authority.surge.sh/imgs/icon/pause.svg" alt="pause-icon">
                            </button>
                            <div class="slider__content">
                                <#list songs as song>
                                    <img class="img slider__img" src="${song.album.pathToCover}" alt="cover">
                                </#list>
                            </div>
                        </div>

                        <div class="player__controls">
                            <button class="player__button back">
                                <img class="img" src="http://physical-authority.surge.sh/imgs/icon/back.svg"
                                     alt="back-icon">
                            </button>
                            <p class="player__context slider__context">
                                <strong class="slider__name"></strong>
                                <span class="player__title slider__title"></span>
                            </p>
                            <button class="player__button next">
                                <img class="img" src="http://physical-authority.surge.sh/imgs/icon/next.svg"
                                     alt="next-icon">
                            </button>
                            <div class="progres">
                                <div class="progres__filled"></div>
                            </div>
                        </div>
                    </div>

                    <ul class="player__playlist list">
                        <#list songs as song>
                            <li class="player__song">
                                <img class="player__img img" src="${song.album.pathToCover}" alt="cover">
                                <p class="player__context">
                                    <b class="player__song-name">${song.name}</b>
                                    <span class="flex">
                                        <span class="player__title"><#list song.getSinger() as singer><#if singer_has_next>${singer.name + ", "}<#else>${singer.name}</#if></#list></span>
                                        <span class="player__song-time">
                                    </span>
                                </span>
                                </p>
                                <div class="star-checkbox">
                                    <input type="checkbox" id="star ${song.id}">
                                    <label for="star ${song.id}">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20"
                                             viewBox="0 0 24 24"
                                             fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round"
                                             stroke-linejoin="round" class="feather feather-star">
                                            <polygon
                                                    points="12 2 15.09 8.26 22 9.27 17 14.14 18.18 21.02 12 17.77 5.82 21.02 7 14.14 2 9.27 8.91 8.26 12 2"/>
                                        </svg>
                                    </label>
                                </div>
                                <audio class="audio" src="${song.path}"></audio>
                            </li>
                        </#list>
                    </ul>
                </div>

            </div>
        </div>
    </div>
</div>

<div class="form" id="form">
    <form action="/add-playlist" method="post">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
        <div class="name">
            <p class="playlist-name">Название плейлиста</p>
            <input type="text" name="playlistName" class="playlist-name-input">
        </div>
        <div class="private">
            <p>Приватный</p>
            <input class="tgl tgl-flat" id="checkbox" name="isPrivate" type="checkbox"/>
            <label class="tgl-btn" for="checkbox"></label>
        </div>
        <div class="buttons">
            <input type="button" name="hide" value="Закрыть" class="hide-btn" onclick="hide_update_visibility('form')">
            <input type="submit" name="submit" value="Создать" class="submit-btn">
        </div>
    </form>
</div>

<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>
<script rel="script" src="/js/account.js"></script>
<script src="/js/header.js"></script>
<script src="/js/player.js"></script>
</body>
</html>
