package ru.egorov.ProfitSeeking.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.egorov.ProfitSeeking.client.StoreCrawlerClient;
import ru.egorov.ProfitSeeking.dto.ProductDto;
import ru.egorov.ProfitSeeking.mapper.ItemMapper;
import ru.egorov.ProfitSeeking.model.Item;
import ru.egorov.ProfitSeeking.model.Offer;
import ru.egorov.ProfitSeeking.model.StoreType;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DbUpdater {
    private final ItemService itemService;
    private final StoreCrawlerClient crawlerClient;
    private final ItemMapper itemMapper;
    private final List<StoreType> stores = List.of(StoreType.footbox, StoreType.sneakerhead);

    @Scheduled(cron = "0 0 6 * * ?")
    public void update() {
        List<Item> items = new ArrayList<>();

        stores.stream()
                .flatMap(store -> crawlerClient.getMany(store, null, null)
                        .getProducts()
                        .stream())
                .forEach(productDto -> addItem(items, productDto));

        itemService.updateAll(items);
    }

    private void addItem(List<Item> items, ProductDto productDto) {
        Item item = prepareItem(items, productDto);
        Offer offer = itemMapper.toOffer(productDto);

        offer.setItem(item);
        item.getOffers()
                .add(offer);

        items.add(item);
    }

    private Item prepareItem(List<Item> items, ProductDto productDto) {
        return items.stream()
                .filter(item -> item.getSku()
                        .equals(productDto.getSku()))
                .findFirst()
                .orElse(itemMapper.toItem(productDto));
    }
}
