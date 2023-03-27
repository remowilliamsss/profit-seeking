package ru.egorov.profitseeking.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class ItemResponse {

    private List<ItemDto> itemDtos;

    private int pageCount;
}
