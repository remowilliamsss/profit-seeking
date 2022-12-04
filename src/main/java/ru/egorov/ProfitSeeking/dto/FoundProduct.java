package ru.egorov.ProfitSeeking.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FoundProduct {
    private String name;
    private String sku;
    private String image;
    private String category;
    private String brand;
    private String color;
    private String country;
    private String gender;
    private List<ProductDifferences> differences;
}
