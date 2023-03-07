package ru.egorov.ProfitSeeking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.egorov.ProfitSeeking.model.Offer;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Integer> {
}
