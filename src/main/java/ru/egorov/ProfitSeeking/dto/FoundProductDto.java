package ru.egorov.ProfitSeeking.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class FoundProductDto {
    private String name;
    private String sku;
    private String image;
    private String category;
    private String brand;
    private String color;
    private String country;
    private String gender;
    private List<Difference> differences;

    @Getter
    public static class Difference {
        private String storeType;
        private Double price;
        private String priceCurrency;
        private String size;
        private String url;
    }
}
