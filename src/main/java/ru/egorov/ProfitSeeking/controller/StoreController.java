package ru.egorov.ProfitSeeking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import ru.egorov.ProfitSeeking.dto.product.ProductResponse;

import java.util.List;

@Controller
@RequestMapping("/stores")
public class StoreController {

    @GetMapping("/{store}")
    public String storePage (@PathVariable("store") String store) {
        return "redirect:" + store + "/1";
    }

    @GetMapping("/{store}/{page}")
    public String storePage(@PathVariable("store") String store, @PathVariable("page") Integer page, Model model) {
        model.addAttribute("store", store);
        model.addAttribute("logo", "/store_logo/" + store + ".png");
        model.addAttribute("page", page);
        model.addAttribute("pagination", List.of(1, 2, 3, 4, 5, 6, 7));

        String url = "http://localhost:8080/api/products?store=" + store + "&page=" + (page - 1) + "&size=30";

        RestTemplate restTemplate = new RestTemplate();

        ProductResponse response = restTemplate.getForObject(url, ProductResponse.class);

        model.addAttribute("products", response.getProducts());
        model.addAttribute("pageCount", response.getPageCount());

        return "store";
    }
}
