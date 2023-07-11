package com.example.Schedule.controllers;


import com.example.Schedule.models.User;
import com.example.Schedule.repositories.UserRepository;

import com.example.Schedule.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.lang.model.element.Name;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;



    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @GetMapping("/sss")
    public String sss() {
        return "hello";
    }

    @PostMapping("/registration")
    public String createUser(User user) {
        userService.createUser(user);
        return "redirect:/login";
    }

}



