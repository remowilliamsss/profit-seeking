package ru.egorov.ProfitSeeking.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping()
public class SearchController {

    @GetMapping("/")
    public String index() {
        return "redirect:search";
    }

    @GetMapping("/search")
    public String search() {
        return "search";
    }

    @PostMapping("/search")
    public String search(Model model, @RequestParam("query") String query) {
        String url = "http://localhost:8080/api/products/search";

        RestTemplate restTemplate = new RestTemplate();

        var foundProductDtos = restTemplate.postForObject(url, createRequest(query), List.class);

        model.addAttribute("results", foundProductDtos);

        return "search";
    }

    @GetMapping("/products/{sku}")
    public String show(@PathVariable("sku") String sku, Model model) {
        String url = "http://localhost:8080/api/products/find_by_sku";

        RestTemplate restTemplate = new RestTemplate();

        var foundProductDtos = restTemplate.postForObject(url, createRequest(sku), List.class);

        model.addAttribute("item", foundProductDtos.stream()
                .findFirst()
                .orElseThrow());

        return "product";
    }

    private HttpEntity<Map<String, String>> createRequest(String jsonValue) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HashMap<String, String> jsonData = new HashMap<>();
        jsonData.put("query", jsonValue);

        return new HttpEntity<>(jsonData, headers);
    }
}
