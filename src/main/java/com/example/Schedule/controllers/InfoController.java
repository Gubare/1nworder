package com.example.Schedule.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

@Controller

public class InfoController {
    @GetMapping("/about")
    public String obo(Model model) {
        model.addAttribute("title", "О нас");
        return "about";
    }

}
