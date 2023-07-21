package com.example.Schedule.controllers;
import com.example.Schedule.models.User;
import com.example.Schedule.models.Wordsa;
import com.example.Schedule.models.Wordsb;
import com.example.Schedule.models.Wordsc;
import com.example.Schedule.repositories.WordsbRepository;
import com.example.Schedule.repositories.WordscRepository;
import com.example.Schedule.repositories.WordsaRepository;
import lombok.SneakyThrows;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;

import java.io.*;
//import org.python.core.PyObject;
//import org.python.core.PyString;
//import org.python.util.PythonInterpreter;
import javax.sound.sampled.*;
import java.lang.ProcessBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.SimpleScriptContext;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

@Controller

public class WordController {

    @Autowired
    private WordsbRepository wordsbRepository;
    @Autowired
    private WordsaRepository wordsaRepository;
    @Autowired
    private WordscRepository wordscRepository;

    @GetMapping("/word/{difficult}")
    public String word(@PathVariable(value = "difficult") Integer difficult, Model model) {
        int id = (int) (Math.random()*66+1);
        if(difficult == 0){return "redirect:/account/{difficult}";}
        if(difficult == 1){
            Optional<Wordsa> word =  wordsaRepository.findById(Long.valueOf(id));
            ArrayList<Wordsa> res = new ArrayList<>();
            word.ifPresent(res::add);
            model.addAttribute("words", res);
        }
        if(difficult == 2){
            Optional<Wordsb> word =  wordsbRepository.findById(Long.valueOf(id));
            ArrayList<Wordsb> res = new ArrayList<>();
            word.ifPresent(res::add);
            model.addAttribute("words", res);
        }
        if(difficult ==  3){
            Optional<Wordsc> word =  wordscRepository.findById(Long.valueOf(id));
            ArrayList<Wordsc> res = new ArrayList<>();
            word.ifPresent(res::add);
            model.addAttribute("words", res);
        }
        model.addAttribute("difficult", difficult);
        model.addAttribute("title", "Слова");
        return "word";
    }
    @SneakyThrows
    @GetMapping("/result/{word}/{difficult}/{id}")
    public String result( @PathVariable(value = "difficult") Integer difficult,@PathVariable(value = "id") long id, @PathVariable(value = "word") String word,Model model){
        StringBuffer fileData = new StringBuffer();
        BufferedReader reader = new BufferedReader(
                new FileReader("C:\\Temp\\result.txt"));
        char[] buf = new char[1024];
        int numRead=0;
        while((numRead=reader.read(buf)) != -1){
            String readData = String.valueOf(buf, 0, numRead);
            fileData.append(readData);
        }
        reader.close();
        System.out.println(fileData);
        if(difficult == 1){
            Optional<Wordsa> trans =  wordsaRepository.findById(Long.valueOf(id));
            ArrayList<Wordsa> res = new ArrayList<>();
            trans.ifPresent(res::add);
            model.addAttribute("word", res);
        }
        if(difficult == 2){
            Optional<Wordsb> trans =  wordsbRepository.findById(Long.valueOf(id));
            ArrayList<Wordsb> res = new ArrayList<>();
            trans.ifPresent(res::add);
            model.addAttribute("word", res);
        }
        if(difficult ==  3){
            Optional<Wordsc> trans =  wordscRepository.findById(Long.valueOf(id));
            ArrayList<Wordsc> res = new ArrayList<>();
            trans.ifPresent(res::add);
            model.addAttribute("word", res);
        }
        String out = new String(fileData);
        String out2 = out.replace('0', ' ');
        String out3 = out2.replace('.', ' ');
        if (out3.length() >= 2){
            char code = out3.charAt(2);
            char code2 = out3.charAt(3);
            String miss = new String();
            miss += code;
            miss += code2;
            model.addAttribute("score", miss);
        }
        if (out3.length() == 1){
            char code = out3.charAt(2);
            char code2 = '0';
            String miss = new String();
            miss += code;
            miss += code2;
            model.addAttribute("score", miss);
        }


        model.addAttribute("title", "результат тестирования");
        model.addAttribute("difficult", difficult);
        return "result";
    }

    @SneakyThrows
    @PostMapping("/result/{word}/{difficult}/{id}")
    public String res(@PathVariable(value = "word") String word, @PathVariable(value = "difficult") String difficult, @RequestParam File audio,Model model){
        String sourceFile = "C://Users//Artic//Downloads//Telegram Desktop//audio.mp3";
        String destinationFile = "C:/Temp/audio.mp3";
        try {
            Path sourcePath = Paths.get(sourceFile);
            Path destinationPath = Paths.get(destinationFile);
            // Копирование файла с помощью метода Files.copy()
            Files.copy(sourcePath, destinationPath);
            System.out.println("Файл успешно скопирован.");
        } catch (IOException e) {
            e.printStackTrace();
        }


        String data = word;
        FileOutputStream out = new FileOutputStream("c://temp//word.txt");
        out.write(data.getBytes());
        out.close();

        StringWriter writer = new StringWriter(); //ouput will be stored here
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptContext context = new SimpleScriptContext();
        context.setWriter(writer); //configures output redirection
        ScriptEngine engine = manager.getEngineByName("python");
        engine.eval(new FileReader(
                "C:\\Users\\Artic\\IdeaProjects\\Schedule2\\src\\" +
                        "main\\java\\com\\example\\" +
                        "Schedule\\controllers\\numbers.py "), context);
        System.out.println(writer.toString());
        model.addAttribute("title", "Переход к результату");
        return "redirect:/result/{word}/{difficult}/{id}";
    }

    @PostMapping("/word/{difficult}")
    public String refresh(@PathVariable(value = "difficult") Integer difficult, Model model) {
        return "redirect:/word/{difficult}";
    }



}
