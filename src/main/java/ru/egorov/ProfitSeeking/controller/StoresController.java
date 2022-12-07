package ru.egorov.ProfitSeeking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import ru.egorov.ProfitSeeking.dto.ProductDto;

import java.util.List;

@Controller
@RequestMapping("/stores")
public class StoresController {

    @GetMapping("/{store}")
    public String storePage (@PathVariable("store") String store) {
        return "redirect:" + store + "/1";
    }

    @GetMapping("/{store}/{page}")
    public String storePage(@PathVariable("store") String store, @PathVariable("page") Integer page, Model model) {
        model.addAttribute("store", store);
        model.addAttribute("logo", "/store_logo/" + store + ".png");
        model.addAttribute("page", page);
        model.addAttribute("numberOfPages", getNumberOfPages(store));
        model.addAttribute("pagination", List.of(1, 2, 3, 4, 5, 6, 7));

        String url = "http://localhost:8080/api/products/" + store + "?page=" + (page - 1) + "&productsPerPage=30";

        RestTemplate restTemplate = new RestTemplate();

        List<ProductDto> response = restTemplate.getForObject(url, List.class);

        model.addAttribute("products", response);

        return "store";
    }

    private int getNumberOfPages(String store) {
        String url = "http://localhost:8080/api/products/" + store;

        RestTemplate restTemplate = new RestTemplate();

        int numberOfProducts =  restTemplate.getForObject(url, List.class).size();

        int numberOfPages = numberOfProducts / 30;

        if (numberOfProducts % 30 != 0) {
            numberOfPages++;
        }

        return  numberOfPages;
    }
}
