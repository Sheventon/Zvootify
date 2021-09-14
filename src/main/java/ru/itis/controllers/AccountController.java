package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.dto.PlaylistDto;
import ru.itis.models.User;
import ru.itis.security.details.UserDetailsImpl;
import ru.itis.services.PlaylistsService;
import ru.itis.services.UsersService;

@Controller
public class AccountController {

    @Autowired
    private UsersService usersService;
    @Autowired
    private PlaylistsService playlistsService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/account")
    public String getProfilePage(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        User user = usersService.getByEmail(userDetails.getUsername());
        model.addAttribute("user", user);
        model.addAttribute("albums", user.getAlbums());
        model.addAttribute("playlists", user.getPlaylists());
        model.addAttribute("songs", user.getSongs());
        return "account";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/add-playlist")
    public String addPlaylist(@AuthenticationPrincipal UserDetailsImpl userDetails, PlaylistDto playlistDto) {
        playlistsService.addPlaylist(playlistDto, userDetails);
        return "redirect:/account";
    }
}
