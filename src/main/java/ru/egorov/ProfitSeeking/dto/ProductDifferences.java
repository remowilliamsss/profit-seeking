package ru.egorov.ProfitSeeking.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDifferences {
    private String storeName;
    private Double price;
    private String priceCurrency;
    private String sizes;
    private String url;
}
