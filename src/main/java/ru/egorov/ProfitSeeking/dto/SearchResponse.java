package ru.egorov.ProfitSeeking.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SearchResponse {
    private List<FoundProduct> foundProductList;
}
