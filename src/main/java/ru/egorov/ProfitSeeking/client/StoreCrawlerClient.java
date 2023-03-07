package ru.egorov.ProfitSeeking.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.egorov.ProfitSeeking.dto.ProductResponse;
import ru.egorov.ProfitSeeking.model.StoreType;

@FeignClient("store-crawler")
public interface StoreCrawlerClient {

    @GetMapping("api/products")
    ProductResponse getMany(@RequestParam(name = "store") StoreType store,
                            @RequestParam(name = "page") Integer page,
                            @RequestParam(name = "size") Integer size);
}
