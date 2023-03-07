package ru.egorov.ProfitSeeking.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.egorov.ProfitSeeking.model.StoreType;
import ru.egorov.ProfitSeeking.service.ItemService;

import java.util.List;

@Controller
@RequestMapping()
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @GetMapping("/")
    public String index() {
        return "redirect:search";
    }

    @GetMapping("/search")
    public String search() {
        return "search";
    }

    @PostMapping("/search")
    public String search(Model model, @RequestParam("query") String query, Pageable pageable) {
        model.addAttribute("results", itemService.findAllByName(query, pageable));

        return "search";
    }

    @GetMapping("/items/{sku}")
    public String show(@PathVariable("sku") String sku, Model model) {
        model.addAttribute("item", itemService.findBySku(sku));

        return "item";
    }

    @GetMapping("stores/{store}")
    public String storePage (@PathVariable("store") StoreType store) {
        return "redirect:" + store + "/1";
    }

    @GetMapping("stores/{store}/{page}")
    public String storePage(@PathVariable("store") StoreType store, @PathVariable("page") Integer page,
                            Model model) {
        Pageable pageable = PageRequest.of(page - 1, 30);

        model.addAttribute("store", store);
        model.addAttribute("logo", "/store_logo/" + store + ".png");
        model.addAttribute("page", page);
        model.addAttribute("pagination", List.of(1, 2, 3, 4, 5, 6, 7));

        var productPage = itemService.findAllByStore(store, pageable);

        model.addAttribute("items", productPage.getContent());
        model.addAttribute("pageCount", productPage.getTotalPages());

        return "store";
    }
}
