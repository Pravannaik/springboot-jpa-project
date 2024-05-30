package com.scratchmachine.scratchmachine.helper;

import com.scratchmachine.scratchmachine.dto.request.AllocateScratchCardRequest;
import com.scratchmachine.scratchmachine.entity.Scratchcards;
import com.scratchmachine.scratchmachine.entity.Users;
import com.scratchmachine.scratchmachine.repository.ScratchCardRepository;
import com.scratchmachine.scratchmachine.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ScratchCardHelper {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ScratchCardRepository scratchCardRepository;


    public void allocateScratchCardToUser(AllocateScratchCardRequest request) {

        Optional<Users> user = userRepository.findByUserId(request.getUserId());

        if (user.isEmpty()) {
            throw new EntityNotFoundException("User not found for given userId: "
                    .concat(String.valueOf(request.getUserId())));
        }

        Optional<List<Scratchcards>> scratchCardsList = scratchCardRepository
                .findByUserAndLimit(PageRequest.of(0, request.getNumberOfScratchCardToAllocate()));

        if (scratchCardsList.isEmpty()) {
            throw new EntityNotFoundException("No scratch card available to assign for given userId: "
                    .concat(String.valueOf(request.getUserId())));
        }

        for (Scratchcards scratchCards : scratchCardsList.get()) {
            scratchCards.setUsers(user.get());

            scratchCardRepository.save(scratchCards);
        }
    }
}
