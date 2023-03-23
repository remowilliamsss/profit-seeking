package ru.egorov.profitseeking.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OfferDto {

    @NotNull
    private Double price;

    @NotNull
    private String priceCurrency;

    private String size;

    @NotNull
    private StoreType storeName;

    @NotEmpty
    private String url;
}
