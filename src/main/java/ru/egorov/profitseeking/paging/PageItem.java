package ru.egorov.profitseeking.paging;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PageItem {

    private PageItemType pageItemType;

    private int index;

    private boolean active;
}
