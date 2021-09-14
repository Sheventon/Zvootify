package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.dto.PlaylistAndSongDto;
import ru.itis.services.PlaylistsService;

@RestController
public class AddSongInPlaylistController {

    @Autowired
    private PlaylistsService playlistsService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/playlist/add-song")
    public ResponseEntity<String> addSongToPlaylist(@RequestBody PlaylistAndSongDto playlistAndSongDto) {
        return ResponseEntity.ok(playlistsService.addSongToPlaylist(playlistAndSongDto.getPlaylistId(), playlistAndSongDto.getSongId()));
    }
}
