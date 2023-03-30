package ru.egorov.profitseeking.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.egorov.profitseeking.client.SearchServiceClient;
import ru.egorov.profitseeking.dto.ItemDto;
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
    public String search(@RequestParam(value = "q", required = false) String query, Model model,
                         @PageableDefault(size = 30) Pageable pageable) {
        if (query == null) {
            return "search";
        }

        model.addAttribute("query", query);
        model.addAttribute("page", pageable.getPageNumber());
        model.addAttribute("pagination", List.of(0, 1, 2, 3, 4, 5, 6));

        Page<ItemDto> page = searchServiceClient.search(query, pageable);

        model.addAttribute("results", page.getContent());
        model.addAttribute("pageCount", page.getTotalPages());
        return "search";
    }

    @GetMapping("/items/{sku}")
    public String show(@PathVariable("sku") String sku, Model model) {
        model.addAttribute("item", searchServiceClient.show(sku));

        return "item";
    }

    @GetMapping("stores/{store}")
    public String storePage(@PathVariable("store") StoreType store, Model model,
                            @PageableDefault(size = 30) Pageable pageable) {
        model.addAttribute("store", store);
        model.addAttribute("page", pageable.getPageNumber());
        model.addAttribute("logo", "/store_logo/" + store + ".png");
        model.addAttribute("pagination", List.of(0, 1, 2, 3, 4, 5, 6));

        Page<ItemDto> page = searchServiceClient.storePage(store, pageable);

        model.addAttribute("items", page.getContent());
        model.addAttribute("pageCount", page.getTotalPages());

        return "store";
    }
}
