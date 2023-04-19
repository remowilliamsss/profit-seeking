package ru.egorov.profitseeking.paging;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
@AllArgsConstructor
public class Paged<T> {

    private Page<T> page;

    private Paging paging;
}
