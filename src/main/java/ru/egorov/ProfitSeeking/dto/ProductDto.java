package ru.egorov.ProfitSeeking.dto;

import lombok.Getter;
import ru.egorov.ProfitSeeking.model.StoreType;

@Getter
public class ProductDto {
    private String name;
    private String sku;
    private String category;
    private String brand;
    private String image;
    private String color;
    private Double price;
    private String priceCurrency;
    private String country;
    private String size;
    private String gender;
    private String composition;
    private String coloring;
    private String url;
    private StoreType storeType;
}
