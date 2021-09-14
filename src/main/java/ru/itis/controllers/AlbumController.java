package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.models.User;
import ru.itis.security.details.UserDetailsImpl;
import ru.itis.services.AlbumsService;
import ru.itis.services.UsersService;

@Controller
public class AlbumController {

    @Autowired
    private AlbumsService albumsService;
    @Autowired
    private UsersService usersService;

    @GetMapping("/album/{singer}/{albumName}")
    public String getAlbumPage(@AuthenticationPrincipal UserDetailsImpl userDetails,
                               Model model, @PathVariable String singer,
                               @PathVariable String albumName) {
        model.addAttribute("user", usersService.getByEmail(userDetails.getUsername()));
        model.addAttribute("album", albumsService.getAlbumByNameAndSinger(singer, albumName));
        return "album";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/buy-album/{id}")
    public String buyAlbum(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable String id) {
        User user = usersService.getByEmail(userDetails.getUsername());
        usersService.buyAlbum(user, Long.parseLong(id));
        return "redirect:/account";
    }
}
