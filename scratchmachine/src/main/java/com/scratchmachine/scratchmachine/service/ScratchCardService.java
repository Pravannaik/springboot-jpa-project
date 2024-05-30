package com.scratchmachine.scratchmachine.service;

import com.scratchmachine.scratchmachine.dto.request.AllocateScratchCardRequest;
import com.scratchmachine.scratchmachine.dto.request.GenerateScratchCardRequest;

public interface ScratchCardService {
    void generateScratchCards(GenerateScratchCardRequest request);

    void allocateScratchCards(AllocateScratchCardRequest request);

    void loadScratchCardData();

    void deleteAll();
}
