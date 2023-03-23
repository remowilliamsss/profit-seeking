package ru.egorov.profitseeking.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.egorov.profitseeking.client.SearchServiceClient;
import ru.egorov.profitseeking.dto.ItemResponse;
import ru.egorov.profitseeking.dto.StoreType;

import java.util.List;

@Controller
@RequestMapping()
@RequiredArgsConstructor
public class ProfitSeekingController {
    private final SearchServiceClient searchServiceClient;

    @GetMapping("/")
    public String index() {
        return "redirect:search";
    }

    @GetMapping("/search")
    public String search() {
        return "search";
    }

    @PostMapping("/search")
    public String search(@RequestParam("query") String query) {
        return "redirect:search/" + query + "/1";
    }

    @GetMapping("/search/{query}/{page}")
    public String search(@PathVariable("query") String query, @PathVariable("page") Integer page, Model model) {
        model.addAttribute("page", page);
        model.addAttribute("query", query);
        model.addAttribute("pagination", List.of(1, 2, 3, 4, 5, 6, 7));

        ItemResponse response = searchServiceClient.search(query, page - 1, 30);

        model.addAttribute("results", response.getItemDtos());
        model.addAttribute("pageCount", response.getPageCount());

        return "search";
    }

    @GetMapping("/items/{sku}")
    public String show(@PathVariable("sku") String sku, Model model) {
        model.addAttribute("item", searchServiceClient.show(sku));

        return "item";
    }

    @GetMapping("stores/{store}")
    public String storePage (@PathVariable("store") StoreType store) {
        return "redirect:" + store + "/1";
    }

    @GetMapping("stores/{store}/{page}")
    public String storePage(@PathVariable("store") StoreType store, @PathVariable("page") Integer page,
                            Model model) {
        model.addAttribute("store", store);
        model.addAttribute("logo", "/store_logo/" + store + ".png");
        model.addAttribute("page", page);
        model.addAttribute("pagination", List.of(1, 2, 3, 4, 5, 6, 7));

        ItemResponse response = searchServiceClient.storePage(store, page - 1, 30);

        model.addAttribute("items", response.getItemDtos());
        model.addAttribute("pageCount", response.getPageCount());

        return "store";
    }
}
