package ru.itis.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AccessDeniedController {

    @GetMapping("/accessDenied")
    public String getAccessDeniedPage(Model model, HttpServletRequest request) {
        model.addAttribute("prevUrl", request.getHeader("referer"));
        return "404";
    }
}
