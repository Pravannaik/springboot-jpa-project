package com.scratchmachine.scratchmachine.service.impl;

import com.scratchmachine.scratchmachine.dto.request.AllocateScratchCardRequest;
import com.scratchmachine.scratchmachine.dto.request.GenerateScratchCardRequest;
import com.scratchmachine.scratchmachine.entity.Scratchcards;
import com.scratchmachine.scratchmachine.helper.ScratchCardHelper;
import com.scratchmachine.scratchmachine.repository.ScratchCardRepository;
import com.scratchmachine.scratchmachine.service.ScratchCardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ScratchCardServiceImpl implements ScratchCardService {

    @Autowired
    private ScratchCardRepository scratchCardRepository;

    @Autowired
    private ScratchCardHelper scratchCardHelper;

    @Override
    public void generateScratchCards(GenerateScratchCardRequest request) {

        List<Scratchcards> scratchCardsList = new ArrayList<>();
        LocalDate localDate = LocalDate.now();

        if (request.isDeactivateOldScratchCard()) {
            //Deactivate all unused expired scratch cards
            Optional<List<Scratchcards>> expiredScratchCards = scratchCardRepository.findByExpiryDateLessThanToday(localDate);

            //delete all expired unused scratch cards
            expiredScratchCards.ifPresent(scratchCards -> scratchCardRepository.deleteAll(scratchCards));
        }

        //Adding no. of days to scratch card generated days to get expiry date
        LocalDate expiryDate = localDate.plusDays(request.getDaysToExpire());

        for (int i = 1; i <= request.getNumberOfScratchCard(); i++) {
            scratchCardsList.add(new Scratchcards(request.getDiscount(), expiryDate, true));
        }

        scratchCardRepository.saveAll(scratchCardsList);
    }

    @Override
    public void allocateScratchCards(AllocateScratchCardRequest request) {
        scratchCardHelper.allocateScratchCardToUser(request);
    }

    @Override
    public void loadScratchCardData() {

        List<Scratchcards> scratchCardsList = new ArrayList<>();
        scratchCardsList.add(new Scratchcards(BigDecimal.valueOf(10), LocalDate.of(2024, 5, 25), true));
        scratchCardsList.add(new Scratchcards(BigDecimal.valueOf(10), LocalDate.of(2024, 5, 25), true));
        scratchCardsList.add(new Scratchcards(BigDecimal.valueOf(10), LocalDate.of(2024, 5, 25), true));
        scratchCardsList.add(new Scratchcards(BigDecimal.valueOf(10), LocalDate.of(2024, 5, 25), true));
        scratchCardsList.add(new Scratchcards(BigDecimal.valueOf(10), LocalDate.of(2024, 5, 25), true));

        scratchCardRepository.saveAll(scratchCardsList);
    }

    @Override
    public void deleteAll() {
        scratchCardRepository.deleteAll();
    }
}
