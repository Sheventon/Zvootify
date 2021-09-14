package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.dto.UserSignUpForm;
import ru.itis.services.UsersService;

import javax.annotation.security.PermitAll;
import javax.validation.Valid;

@Controller
public class SignUpController {

    @Autowired
    private UsersService usersService;

    @PermitAll
    @GetMapping("/signUp")
    public String getSignUpPage(Model model) {
        model.addAttribute("userSignUpForm", new UserSignUpForm());
        return "register";
    }

    @PermitAll
    @PostMapping("/signUp")
    public String signUp(@Valid UserSignUpForm userForm, BindingResult bindingResult, Model model) {
        return usersService.signUp(userForm, bindingResult, model);
    }
}
