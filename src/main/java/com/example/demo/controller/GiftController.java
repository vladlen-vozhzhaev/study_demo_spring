package com.example.demo.controller;

import com.example.demo.entity.Gift;
import com.example.demo.repository.GiftRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GiftController {
    private final GiftRepository giftRepository;

    public GiftController(GiftRepository giftRepository) {
        this.giftRepository = giftRepository;
    }

    @GetMapping("/")
    public String mainPage(){
        return "index";
    }
    @GetMapping("/add")
    public String showAddGift(Model model){
        model.addAttribute("gift", new Gift());
        return "add-gift";
    }
    @PostMapping("/add") // Обработчик данных с формы
    public String addGift(@ModelAttribute Gift gift){
        giftRepository.save(gift);
        return "redirect:/add";
    }
}
