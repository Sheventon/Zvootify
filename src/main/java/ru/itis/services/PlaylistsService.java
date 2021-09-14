package ru.itis.services;

import org.springframework.ui.Model;
import ru.itis.dto.PlaylistDto;
import ru.itis.models.Playlist;
import ru.itis.security.details.UserDetailsImpl;

import java.util.List;

public interface PlaylistsService {
    List<Playlist> getPublicPlaylists();

    Playlist getById(Long id);

    Long countOfPlaylists();

    Playlist getPlaylistByNameAndUser(String name, Long id, Model model);

    Playlist getPlaylistByNameAndUser(String name, Long id);

    //String getAddPlaylistPage(UserDetailsImpl userDetails, Model model);

    //String addPlaylist(PlaylistDto playlistDto, User user);

    void addPlaylist(PlaylistDto playlistDto, UserDetailsImpl userDetails);

    String changePrivateState(Long id);

    String addSongToPlaylist(Long playlistId, Long songId);

    void changePrivateStateless(Long id);
}
