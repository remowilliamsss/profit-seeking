package ru.egorov.profitseeking.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import ru.egorov.profitseeking.config.FeignClientConfiguration;
import ru.egorov.profitseeking.dto.ItemDto;
import ru.egorov.profitseeking.dto.ItemResponse;
import ru.egorov.profitseeking.dto.StoreType;

@FeignClient(name = "search-service", configuration = FeignClientConfiguration.class)
public interface SearchServiceClient {

    @GetMapping("api/search")
    ItemResponse search(@RequestParam(name = "query") String query,
                        @RequestParam(name = "page") Integer page,
                        @RequestParam(name = "size") Integer size);

    @GetMapping("api/items/{sku}")
    public ItemDto show(@PathVariable("sku") String sku);

    @GetMapping("api/stores/{store}")
    public ItemResponse storePage(@PathVariable("store") StoreType store,
                                  @RequestParam(name = "page") Integer page,
                                  @RequestParam(name = "size") Integer size);
}
