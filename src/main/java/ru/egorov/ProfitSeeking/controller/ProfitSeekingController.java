package ru.egorov.ProfitSeeking.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.egorov.ProfitSeeking.dto.FoundProduct;
import ru.egorov.ProfitSeeking.dto.SearchResponse;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping()
public class ProfitSeekingController {

    @GetMapping("/")
    public String index() {
        return "redirect:search";
    }

    @GetMapping("/search")
    public String searchPage() {
        return "search";
    }

    @PostMapping("/search")
    public String search(Model model, @RequestParam("query") String query) {
        String url = "http://localhost:8080/api/products/search";

        RestTemplate restTemplate = new RestTemplate();

        SearchResponse searchResponse = restTemplate.postForObject(url, createRequest(query), SearchResponse.class);

        model.addAttribute("results", searchResponse);

        return "search";
    }

    @GetMapping("/products/{sku}")
    public String show(@PathVariable("sku") String sku, Model model) {
        String url = "http://localhost:8080/api/products/find_by_sku";

        RestTemplate restTemplate = new RestTemplate();

        FoundProduct item = restTemplate.postForObject(url, createRequest(sku), FoundProduct.class);

        model.addAttribute("item", item);

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
