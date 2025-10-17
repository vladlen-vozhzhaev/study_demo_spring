package com.example.demo.controller;

import com.example.demo.entity.Gift;
import com.example.demo.repository.GiftRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

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
    @GetMapping("/gifts")
    public String showAllGift(Model model){
        List<Gift> gifts = giftRepository.findAll();
        model.addAttribute("gifts", gifts);
        return "gift-list";
    }

    @GetMapping("/edit/{id}")
    public String showEditGift(@PathVariable Long id, Model model){
        Gift gift = giftRepository.findById(id).orElseThrow();
        model.addAttribute("gift", gift);
        return "edit-gift";
    }
}
