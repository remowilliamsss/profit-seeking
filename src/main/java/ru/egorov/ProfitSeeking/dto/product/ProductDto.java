package ru.egorov.ProfitSeeking.dto.product;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "product")
@JsonSubTypes({
        @JsonSubTypes.Type(value = FootboxProductDto.class, name = "FootboxProductDto"),
        @JsonSubTypes.Type(value = SneakerheadProductDto.class, name = "SneakerheadProductDto")
})
@Getter
public abstract class ProductDto {
    private String name;
    private String sku;
    private String category;
    private String brand;
    private String image;
    private String color;
    private Double price;
    private String priceCurrency;
    private String country;
    private String sizes;
    private String gender;
    private String url;
}
