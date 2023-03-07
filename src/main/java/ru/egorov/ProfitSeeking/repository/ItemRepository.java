package ru.egorov.ProfitSeeking.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.egorov.ProfitSeeking.model.Item;
import ru.egorov.ProfitSeeking.model.StoreType;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

    Page<Item> findAllByNameContainingIgnoreCase(String name, Pageable pageable);

    Page<Item> findAllByOffersStoreName(StoreType storeName, Pageable pageable);

    Item findBySku(String sku);
}
