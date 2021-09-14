package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.dto.PlaylistCheckboxDto;
import ru.itis.services.PlaylistsService;

@RestController
public class ChangePlaylistPrivateStateController {

    @Autowired
    private PlaylistsService playlistsService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/change-playlist-private-state")
    public ResponseEntity<String> changePlaylistPrivateState(@RequestBody PlaylistCheckboxDto dto) {
        return ResponseEntity.ok(playlistsService.changePrivateState(dto.getId()));
    }
}
