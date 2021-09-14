package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.dto.SongDto;
import ru.itis.models.User;
import ru.itis.security.details.UserDetailsImpl;
import ru.itis.services.UsersService;

@RestController
public class BuySongController {

    @Autowired
    private UsersService usersService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/buy-song")
    public ResponseEntity<String> buyAlbum(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody SongDto songDto) {
        User user = usersService.getByEmail(userDetails.getUsername());
        return ResponseEntity.ok(usersService.buySong(user, songDto.getId()));
    }
}
