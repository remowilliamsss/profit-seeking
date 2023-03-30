package ru.egorov.profitseeking.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import ru.egorov.profitseeking.config.FeignClientConfiguration;
import ru.egorov.profitseeking.dto.ItemDto;
import ru.egorov.profitseeking.dto.StoreType;

@FeignClient(name = "search-service", configuration = FeignClientConfiguration.class)
public interface SearchServiceClient {

    @GetMapping("api/search")
    Page<ItemDto> search(@RequestParam(name = "query") String query, Pageable pageable);

    @GetMapping("api/items/{sku}")
    ItemDto show(@PathVariable("sku") String sku);

    @GetMapping("api/stores/{store}")
    Page<ItemDto> storePage(@PathVariable("store") StoreType store, Pageable pageable);
}
