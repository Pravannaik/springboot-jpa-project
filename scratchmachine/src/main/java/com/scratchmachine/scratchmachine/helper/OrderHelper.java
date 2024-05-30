package com.scratchmachine.scratchmachine.helper;

import com.scratchmachine.scratchmachine.dto.request.ItemRequest;
import com.scratchmachine.scratchmachine.dto.request.OrderRequest;
import com.scratchmachine.scratchmachine.entity.Items;
import com.scratchmachine.scratchmachine.entity.Scratchcards;
import com.scratchmachine.scratchmachine.entity.Users;
import com.scratchmachine.scratchmachine.repository.ItemRepository;
import com.scratchmachine.scratchmachine.repository.ScratchCardRepository;
import com.scratchmachine.scratchmachine.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;

@Component
public class OrderHelper {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ScratchCardRepository scratchCardRepository;

    public boolean isUserActive(OrderRequest request) {

        Optional<Users> user = userRepository.findByUserId(request.getUserId());

        if (user.isEmpty()) {
            throw new EntityNotFoundException("User not found for given userId: "
                    .concat(String.valueOf(request.getUserId())));
        }

        return user.get().isActive();
    }

    public BigDecimal calculateAmountWithoutDiscount(ItemRequest request) {

        Optional<Items> item = itemRepository.findById(request.getItemId());

        if (item.isEmpty()) {
            throw new EntityNotFoundException("Item not found for given itemId: "
                    .concat(String.valueOf(request.getItemId())));
        }

        if (item.get().isActive()) {
            return BigDecimal.valueOf(request.getQuantity()).multiply(item.get().getRate());
        }

        return null;
    }

    public BigDecimal calculateAmountDiscount(ItemRequest request, BigDecimal amtWithoutDiscount) {

        Optional<Scratchcards> scratchcards = scratchCardRepository.findByScratchCardIdAndUsed(request.getScratchCardId(), false);

        if (scratchcards.isEmpty()) {
            throw new EntityNotFoundException("Scratch card not found for given scratchCardId: "
                    .concat(String.valueOf(request.getScratchCardId())));
        }

        BigDecimal discountAmount = amtWithoutDiscount.subtract(scratchcards.get().getDiscount());

        scratchcards.get().setUsed(true);
        scratchCardRepository.save(scratchcards.get());

        return discountAmount;
    }
}
