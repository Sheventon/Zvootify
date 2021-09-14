package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import ru.itis.dto.PlaylistDto;
import ru.itis.models.Playlist;
import ru.itis.models.Song;
import ru.itis.models.User;
import ru.itis.repositories.PlaylistsRepository;
import ru.itis.repositories.SongsRepository;
import ru.itis.repositories.UsersRepository;
import ru.itis.security.details.UserDetailsImpl;

import java.util.*;

@Service
public class PlaylistsServiceImpl implements PlaylistsService {

    @Autowired
    private PlaylistsRepository playlistsRepository;
    @Autowired
    private SongsRepository songsRepository;
    @Autowired
    private UsersRepository usersRepository;

    @Override
    public List<Playlist> getPublicPlaylists() {
        return playlistsRepository.findPublicPlaylists();
    }

    @Override
    public Playlist getById(Long id) {
        Optional<Playlist> optionalPlaylist = playlistsRepository.findById(id);
        return optionalPlaylist.orElse(null);
    }

    @Override
    public Long countOfPlaylists() {
        return playlistsRepository.count();
    }

    @Override
    public Playlist getPlaylistByNameAndUser(String name, Long id, Model model) {
        User user = usersRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("User not found"));
        return playlistsRepository.findByNameAndUser(name, user).orElse(null);
    }

    @Override
    public Playlist getPlaylistByNameAndUser(String name, Long id) {
        User user = usersRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("User not found"));
        return playlistsRepository.findByNameAndUser(name, user).orElse(null);
    }

    @Override
    public void addPlaylist(PlaylistDto playlistDto, UserDetailsImpl userDetails) {
        User user = usersRepository.findByEmail(userDetails.getUsername()).orElseThrow(() ->
                new IllegalArgumentException("User not found"));
        Playlist playlist = Playlist.builder()
                .name(playlistDto.getPlaylistName())
                .user(user)
                .isPrivate(playlistDto.getIsPrivate() != null)
                .songsCount(0)
                .songs(new ArrayList<>())
                .build();
        playlistsRepository.save(playlist);
    }

    @Override
    public String changePrivateState(Long id) {
        Playlist playlist = playlistsRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Playlist not found"));
        playlist.setIsPrivate(!playlist.getIsPrivate());
        playlistsRepository.save(playlist);
        return "correct";
    }

    @Override
    public String addSongToPlaylist(Long playlistId, Long songId) {
        Playlist playlist = playlistsRepository.findById(playlistId).orElseThrow(() ->
                new IllegalArgumentException("Playlist not found"));
        Song song = songsRepository.findById(songId).orElseThrow(() ->
                new IllegalArgumentException("Song not found"));
        playlist.getSongs().add(song);
        playlist.setSongsCount(playlist.getSongsCount() + 1);
        return "song added";
    }

    @Override
    public void changePrivateStateless(Long id) {

    }
}
