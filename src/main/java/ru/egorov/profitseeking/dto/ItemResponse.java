package ru.egorov.profitseeking.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ItemResponse {

    private List<ItemDto> itemDtos;

    private int pageCount;

    {
        itemDtos = Collections.emptyList();
    }
}
