package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import ru.itis.models.Song;
import ru.itis.models.User;
import ru.itis.repositories.SongsRepository;

import java.util.List;

@Service
public class SongsServiceImpl implements SongsService {

    @Autowired
    private SongsRepository songsRepository;

    @Override
    public List<Song> getFirstTenSongs(Model model) {
        return songsRepository.findFirstById();
    }

    @Override
    public List<Song> getUserSongs(User user) {
        return user.getSongs();
    }

    @Override
    public Song getAlbumById(Long id) {
        return songsRepository.findById(id).orElse(null);
    }
}
