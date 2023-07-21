package com.example.Schedule.controllers;
import com.example.Schedule.models.Text;
import com.example.Schedule.models.Wordsa;
import com.example.Schedule.repositories.TextRepository;
import com.example.Schedule.repositories.WordsbRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.util.ArrayList;
import java.util.Optional;

@Controller
public class TextController {

    @Autowired
    private TextRepository textRepository;


    @GetMapping("/text")
    public String TXT(Model model) {
//        int id = (int) (Math.random()*4+1);
        int id = 37;
        Optional<Text> text =  textRepository.findById((long) id);
        ArrayList<Text> res = new ArrayList<>();
        text.ifPresent(res::add);
        model.addAttribute("text", res);
        model.addAttribute("title", "Изучение текстов");
        return "text";
    }


}
