package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.models.User;
import ru.itis.services.UsersService;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private UsersService usersService;

    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String getAdminPage(Model model) {
        List<User> allUsers = usersService.getAllUsers();
        model.addAttribute("users", allUsers);
        return "admin";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/ban/{id}")
    public String banUser(@PathVariable("id") String id) {
        usersService.banUser(Long.parseLong(id));
        return "redirect:/admin";
    }
}
