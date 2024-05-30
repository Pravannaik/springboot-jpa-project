package com.scratchmachine.scratchmachine.helper;

import com.scratchmachine.scratchmachine.dto.response.ReportResponse;
import com.scratchmachine.scratchmachine.entity.Scratchcards;
import com.scratchmachine.scratchmachine.repository.ScratchCardRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;

@Slf4j
@Component
public class ReportHelper {

    @Autowired
    private ScratchCardRepository scratchCardRepository;

    public Collection<ReportResponse> generateUserReport(Long userId) {
        LocalDate localDate = LocalDate.now();

        Map<Long, ReportResponse> reportMap = new HashMap<>();

        Optional<List<Scratchcards>> scratchcards = getScratchCardByUsers(userId);

        if (scratchcards.isEmpty()) {
            throw new EntityNotFoundException("Scratch cards not found for given users");
        }

        try {
            for (Scratchcards cards : scratchcards.get()) {
                ReportResponse reportResponse = new ReportResponse();

                if (reportMap.containsKey(cards.getUsers().getUserId())) {
                    updateReportMap(reportMap, reportResponse, cards, localDate);
                } else {

                    reportResponse.setUserId(cards.getUsers().getUserId());
                    reportResponse.setTotalWon(cards.getDiscount());

                    if (!cards.isUsed() && localDate.isBefore(cards.getScratchCardExpiryDate())) {
                        reportResponse.setTotalUnused(cards.getDiscount());
                    }

                    reportMap.put(reportResponse.getUserId(), reportResponse);
                }
            }

            return reportMap.values();

        } catch (Exception exception) {
            log.error("Exception occurred while generating report for users, exception message: {}",
                    exception.getMessage(), exception);
            return Collections.emptyList();
        }
    }

    private Optional<List<Scratchcards>> getScratchCardByUsers(Long userId) {
        Optional<List<Scratchcards>> scratchcards;

        if (Objects.nonNull(userId)) {
            scratchcards = scratchCardRepository.findByUsersUserId(userId);
        } else {
            scratchcards = scratchCardRepository.findByScratchCardUserId();
        }

        return scratchcards;
    }

    private void updateReportMap(Map<Long, ReportResponse> reportMap, ReportResponse reportResponse,
                                           Scratchcards cards, LocalDate localDate) {

        ReportResponse reportMapResponse = reportMap.get(cards.getUsers().getUserId());

        reportResponse.setUserId(cards.getUsers().getUserId());
        reportResponse.setTotalWon(reportMapResponse.getTotalWon().add(cards.getDiscount()));

        if (!cards.isUsed() && localDate.isBefore(cards.getScratchCardExpiryDate())) {
            reportResponse.setTotalUnused(reportMapResponse.getTotalUnused().add(cards.getDiscount()));
        } else {
            reportResponse.setTotalUnused(reportMapResponse.getTotalUnused());
        }

        reportMap.put(reportResponse.getUserId(), reportResponse);
    }
}
