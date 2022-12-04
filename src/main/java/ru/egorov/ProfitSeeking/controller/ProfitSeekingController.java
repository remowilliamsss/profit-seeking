package ru.egorov.ProfitSeeking.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.egorov.ProfitSeeking.dto.SearchResponse;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/profit-seeking")
public class ProfitSeekingController {

    @GetMapping("/search")
    public String index() {
        return "search";
    }

    @PostMapping("/search")
    public String search(Model model, @RequestParam("query") String query) {
        RestTemplate restTemplate = new RestTemplate();

        String url = "http://localhost:8080/api/products/search";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HashMap<String, String> jsonData = new HashMap<>();
        jsonData.put("query", query);

        HttpEntity<Map<String, String>> request = new HttpEntity<>(jsonData, headers);

        SearchResponse searchResponse = restTemplate.postForObject(url, request, SearchResponse.class);

        model.addAttribute("results", searchResponse);

        return "search";
    }
}
