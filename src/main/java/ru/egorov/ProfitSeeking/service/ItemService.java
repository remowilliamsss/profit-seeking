package ru.egorov.ProfitSeeking.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.egorov.ProfitSeeking.model.Item;
import ru.egorov.ProfitSeeking.model.StoreType;
import ru.egorov.ProfitSeeking.repository.ItemRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemService {
    private final ItemRepository itemRepository;
    private final OfferService offerService;

    public Page<Item> findAll(Pageable pageable) {
        return itemRepository.findAll(pageable);
    }

    public Page<Item> findAllByName(String name, Pageable pageable) {
        return itemRepository.findAllByNameContainingIgnoreCase(name, pageable);
    }

    public Page<Item> findAllByStore(StoreType store, Pageable pageable) {
        return itemRepository.findAllByOffersStoreName(store, pageable);
    }

    public Item findBySku(String sku) {
        return itemRepository.findBySku(sku);
    }

    @Transactional
    public List<Item> updateAll(List<Item> items) {
        offerService.deleteAll();
        itemRepository.deleteAll();

        itemRepository.flush();

        return itemRepository.saveAll(items);
    }
}
