package ru.itis.services;

import org.springframework.ui.Model;
import ru.itis.models.Song;
import ru.itis.models.User;

import java.util.List;

public interface SongsService {
    List<Song> getFirstTenSongs(Model model);

    List<Song> getUserSongs(User user);

    Song getAlbumById(Long id);
}
