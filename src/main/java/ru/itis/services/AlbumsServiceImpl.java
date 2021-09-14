package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.models.Album;
import ru.itis.repositories.AlbumsRepository;

import java.util.List;

@Service
public class AlbumsServiceImpl implements AlbumsService {

    @Autowired
    private AlbumsRepository albumsRepository;

    @Override
    public List<Album> getAllAlbums() {
        return albumsRepository.findAll();
    }

    @Override
    public Album getAlbumById(Long id) {
        return albumsRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Album not found"));
    }

    @Override
    public Long countOfAlbums() {
        return albumsRepository.count();
    }

    @Override
    public Album getAlbumByNameAndSinger(String singer, String albumName) {
        return albumsRepository.findBySingerAndName(singer, albumName).orElseThrow(() ->
                new IllegalArgumentException("Album not found"));
    }
}
