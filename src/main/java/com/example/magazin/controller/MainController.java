package com.example.magazin.controller;

import com.example.magazin.domain.Message;
import com.example.magazin.repos.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class MainController {
    @Autowired
    private MessageRepo messageRepo;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/redirect")
    public String redirect(Map<String, Object> model) {
        return "redirect";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model model) {
        Message message = messageRepo.findById(id);
        model.addAttribute("message", message);
        return "edit";
    }

    @GetMapping("/main")
    public String main(@RequestParam(required = false) String filter, Model model) {
        Iterable<Message> messages = messageRepo.findAll();
        if (filter != null && !filter.isEmpty()) {
            messages = messageRepo.findByNamesOrMaterialOrColorOrDescription(filter, filter, filter, filter);
        } else {
            messages = messageRepo.findAll();
        }
        model.addAttribute("messages", messages);
        model.addAttribute("filter", filter);

        return "main";
    }

    //Добавить
    @PostMapping("/main")
    public String add(@RequestParam String names, @RequestParam String description, @RequestParam String material, @RequestParam String color, @RequestParam int cost, Map<String, Object> model) {
        Message message = new Message(names, description, material, color, cost);
        System.out.println("post");
        messageRepo.save(message);
        Iterable<Message> messages = messageRepo.findAll();
        model.put("messages", messages);
        return "main";
    }

    //Добавить
    @PostMapping("/edit/{id}")
    public String add(@PathVariable int id, @RequestParam String names, @RequestParam String description, @RequestParam String material, @RequestParam String color, @RequestParam int cost, Map<String, Object> model) {
        Message message = messageRepo.findById(id);
        message.setNames(names);
        message.setColor(color);
        message.setCost(cost);
        message.setDescription(description);
        message.setMaterial(material);
        messageRepo.save(message);
        return "redirect";
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable int id, Map<String, Object> model) {
        messageRepo.deleteById(id);
        Iterable<Message> messages = messageRepo.findAll();
        model.put("messages", messages);
        return "main";
    }

    @PostMapping("/basket")
    public String basket(@RequestParam String names, @RequestParam String description, @RequestParam String material, @RequestParam String color, @RequestParam int cost, Map<String, Object> model) {
        Message message = new Message(names, description, material, color, cost);
        messageRepo.save(message);
        Iterable<Message> messages = messageRepo.findAll();
        model.put("messages", messages);
        return "main";


    }
}