package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.security.details.UserDetailsImpl;
import ru.itis.services.*;

@Controller
public class ReviewController {

    @Autowired
    private AlbumsService albumsService;
    @Autowired
    private PlaylistsService playlistsService;
    @Autowired
    private SongsService songsService;
    @Autowired
    private UsersService usersService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/review")
    public String getReviewPage(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        model.addAttribute("user", usersService.getByEmail(userDetails.getUsername()));
        model.addAttribute("albums", albumsService.getAllAlbums());
        model.addAttribute("playlists", playlistsService.getPublicPlaylists());
        model.addAttribute("songs", songsService.getFirstTenSongs(model));
        return "review";
    }

}
