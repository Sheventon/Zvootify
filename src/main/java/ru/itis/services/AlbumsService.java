package ru.itis.services;

import ru.itis.models.Album;

import java.util.List;

public interface AlbumsService {
    List<Album> getAllAlbums();

    Album getAlbumById(Long id);

    Long countOfAlbums();

    Album getAlbumByNameAndSinger(String name, String singer);
}
