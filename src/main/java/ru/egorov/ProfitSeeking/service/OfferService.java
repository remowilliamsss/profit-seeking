package ru.egorov.ProfitSeeking.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.egorov.ProfitSeeking.repository.OfferRepository;

@Service
@RequiredArgsConstructor
public class OfferService {
    private final OfferRepository offerRepository;

    @Transactional
    public void deleteAll() {
        offerRepository.deleteAll();
    }
}
