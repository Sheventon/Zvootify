package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.itis.models.Playlist;
import ru.itis.services.PlaylistsService;
import ru.itis.services.UsersService;

@Controller
public class PlaylistController {

    @Autowired
    private PlaylistsService playlistsService;
    @Autowired
    private UsersService usersService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/playlist/{name}/{id}")
    public String getPlaylistPage(Model model, @PathVariable String name, @PathVariable Long id) {
        Playlist playlist = playlistsService.getPlaylistByNameAndUser(name, id, model);
        model.addAttribute("playlist", playlist);
        model.addAttribute("user", usersService.getById(id));
        return "playlist";
    }
}
