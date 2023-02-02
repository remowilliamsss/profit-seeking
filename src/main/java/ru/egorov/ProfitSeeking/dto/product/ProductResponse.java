package ru.egorov.ProfitSeeking.dto.product;

import lombok.Getter;

import java.util.List;

@Getter
public class ProductResponse {

    private List<ProductDto> products;

    private int pageCount;
}
