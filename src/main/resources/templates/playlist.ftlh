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
    <title>Playlist</title>
    <link rel="stylesheet" href="/css/header.css">
    <link rel="stylesheet" href="/css/playlist.css">
    <link rel="shortcut icon" href="/img/music-folder.png" type="image/x-icon">
</head>
<body>

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
</div>

<div class="main">
    <div class="content">

        <div class="head">
            <#if playlist.songs[0]??>
                <div class="photo"
                     style="border-radius: 30px;background: url('${playlist.songs[0].album.pathToCover}');
                             background-size: cover;
                             background-repeat: no-repeat;
                             background-position: center center;"></div>
            <#else>
                <div></div>
            </#if>
            <div class="main-heading">
                <div class="playlist-name">
                    <p>${playlist.name}</p>
                </div>
                <div class="playlist-owner">
                    <p>${playlist.user.firstName} ${playlist.user.lastName}</p>
                </div>
                <div class="sub-heading">
                    <div class="songs-count">
                        <p>Песен: ${playlist.songsCount}</p>
                    </div>
                    <div class="private">
                        <p>Приватный</p>
                        <#if playlist.isPrivate>
                            <input class="tgl tgl-flat" id="checkbox" type="checkbox"
                                   onclick="change_private(${playlist.id})" checked/>
                        <#else>
                            <input class="tgl tgl-flat" id="checkbox" type="checkbox"
                                   onclick="change_private(${playlist.id})"/>
                        </#if>
                        <label class="tgl-btn" for="checkbox"></label>
                    </div>
                </div>
            </div>
        </div>

        <div class="add-songs">
            <input type="button" class="add" value="Добавить треки" onclick="update_visibility('new-songs')">
        </div>

        <div class="player-content">
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
                            <#list playlist.songs as song>
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
                    <#list playlist.songs as song>
                        <li class="player__song">
                            <img class="player__img img" src="${song.album.pathToCover}" alt="cover">
                            <p class="player__context">
                                <b class="player__song-name">${song.name}</b>
                                <span class="flex">
                                    <span class="player__title"><#list song.getSinger() as singer><#if singer_has_next>${singer.name + ", "}<#else>${singer.name}</#if></#list></span>
                                    <span class="player__song-time"></span>
                                </span>
                            </p>
                            <audio class="audio" src="${song.path}"></audio>
                        </li>
                    </#list>
                </ul>
            </div>
        </div>
    </div>

</div>

<div class="new-songs" id="new-songs">
    <p class="new-songs-head">Добавить песни</p>
    <ul class="all-new-songs">
        <#list user.songs as userSong>
            <#if playlist.songs[0]??>
                <#assign flag = 0>
                <#list playlist.songs as playlistSong>
                    <#if userSong.id == playlistSong.id>
                        <#assign flag = 1>
                        <#break>
                    </#if>
                </#list>
                <#if flag == 0>
                    <li>
                        <div class="song">
                            <div class="song-content">
                                <div class="song-cover" style="content: url('${userSong.album.pathToCover}');
                                        width: 70px;height: 70px;border-radius: 20px;margin-right: 10px;"></div>
                                <div class="song-descr">
                                    <p class="song-name">${userSong.name}</p>
                                    <p class="singer">
                                        <#list userSong.singer as singer>
                                            <#if singer_has_next>
                                                ${singer.name + ", "}
                                            <#else>
                                                ${singer.name}
                                            </#if>
                                        </#list>
                                    </p>
                                </div>
                            </div>
                            <div class="plus">
                                <div class="plus-content" id="plus-content ${userSong.id}"
                                     onclick="add_song(${playlist.id}, ${userSong.id})">
                                </div>
                            </div>
                        </div>
                    </li>
                </#if>
            <#else>
                <li>
                    <div class="song">
                        <div class="song-content">
                            <div class="song-cover" style="content: url('${userSong.album.pathToCover}');
                                    width: 70px;height: 70px;border-radius: 20px;margin-right: 10px;"></div>
                            <div class="song-descr">
                                <p class="song-name">${userSong.name}</p>
                                <p class="singer">
                                    <#list userSong.singer as singer>
                                        <#if singer_has_next>
                                            ${singer.name + ", "}
                                        <#else>
                                            ${singer.name}
                                        </#if>
                                    </#list>
                                </p>
                            </div>
                        </div>
                        <div class="plus">
                            <div class="plus-content" id="plus-content ${userSong.id}"
                                 onclick="add_song(${playlist.id}, ${userSong.id})"></div>
                        </div>
                    </div>
                </li>
            </#if>
        </#list>
    </ul>
    <input type="button" class="hide" value="Закрыть" onclick="document.location.reload()">
</div>

<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script src="/js/header.js"></script>
<script src="/js/player.js"></script>
<script src="/js/account.js"></script>
<script src="/js/change_playlist_private_state.js"></script>
<script src="/js/add_song_in_playlist.js"></script>
</body>
</html>
