package ru.egorov.profitseeking.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ItemDto {

    @NotEmpty
    private String name;

    @NotEmpty
    private String sku;

    private String category;

    private String brand;

    private String image;

    private String color;

    private String country;

    private String gender;

    private String composition;

    private String coloring;

    private List<OfferDto> offers;

    {
        offers = new ArrayList<>();
    }
}
