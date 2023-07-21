package com.example.Schedule.controllers;
import com.example.Schedule.models.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.Schedule.models.User;
import com.example.Schedule.repositories.UserRepository;
import com.example.Schedule.services.UserService;

@Controller
public class AccountController {
    @GetMapping("/account/{difficult}")
    public String LK(@PathVariable(value = "difficult") Integer difficult, Model model, @AuthenticationPrincipal User user) {
        if(difficult == 0){model.addAttribute("com", "Не выбрана для сессии");}
        if(difficult == 1){model.addAttribute("com", "Легко");}
        if(difficult == 2){model.addAttribute("com", "Средне");}
        if(difficult ==  3){model.addAttribute("com", "Сложно");}
        if(difficult > 3){
            return "redirect:/editing";
        }
        if(difficult < 0){
            return "redirect:/editing";
        }
        model.addAttribute("difficult", difficult);
        model.addAttribute("user", user);
        model.addAttribute("title", "Личный кабинет");
        return "account";
    }

    @PostMapping("/accountenter")
    public String ENTLK(Model model, @RequestParam String difficult) {
        if(difficult == ""){model.addAttribute("warning", "Вы забыли ввести уровень сложности!");
            return "redirect:/editing";}
        model.addAttribute("title", "Переход в кабинет из редактирования");
        return "redirect:/account/%s".formatted(difficult);
    }

    @PostMapping("word/accountenter/0")
    public String leaveword(Model model) {
        model.addAttribute("title", "");
        return "redirect:/account/0";
    }



    @GetMapping("/editing")
    public String EDT(Model model) {
        model.addAttribute("title", "Редактирование профиля");
        model.addAttribute("warning", "Введите уровень сложности");
        return "editing";
    }


    @PostMapping("/editing")
    public String LKEDT(Model model) {
        model.addAttribute("title", "Переход в редактирование");
        return "redirect:/editing";
    }

}
