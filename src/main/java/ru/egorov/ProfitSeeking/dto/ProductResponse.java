package ru.egorov.ProfitSeeking.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ProductResponse {

    private List<ProductDto> products;

    private int pageCount;

    {
        products = Collections.emptyList();
    }
}
