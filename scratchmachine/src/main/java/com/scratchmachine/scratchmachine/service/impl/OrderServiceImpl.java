package com.scratchmachine.scratchmachine.service.impl;

import com.scratchmachine.scratchmachine.dto.request.ItemRequest;
import com.scratchmachine.scratchmachine.dto.request.OrderRequest;
import com.scratchmachine.scratchmachine.entity.Orders;
import com.scratchmachine.scratchmachine.exception.UserException;
import com.scratchmachine.scratchmachine.helper.OrderHelper;
import com.scratchmachine.scratchmachine.repository.OrderRepository;
import com.scratchmachine.scratchmachine.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderHelper orderHelper;

    @Override
    public String createOrder(OrderRequest request) {

        BigDecimal amtWithoutDiscount;
        BigDecimal amtWithDiscount;
        BigDecimal totalAmountOnOrder = BigDecimal.ZERO;
        int scratchCardCount = 0;

        if (!orderHelper.isUserActive(request)) {
            //throw user not active Exception
            throw new UserException("Not active user found for creating order, userId: ".concat(String.valueOf(request.getUserId())));
        }

        for (ItemRequest item : request.getItemRequest()) {
            BigDecimal totalAmountPerItem = BigDecimal.ZERO;
            //calculate amount without discount
            amtWithoutDiscount = orderHelper.calculateAmountWithoutDiscount(item);

            if (Objects.nonNull(amtWithoutDiscount)) {
                totalAmountOnOrder = amtWithoutDiscount;

                if (Objects.nonNull(item.getScratchCardId())) {
                    //calculate amount with discount
                    amtWithDiscount = orderHelper.calculateAmountDiscount(item, amtWithoutDiscount);
                    totalAmountOnOrder = amtWithDiscount;
                    scratchCardCount += 1;
                }

                totalAmountOnOrder = totalAmountOnOrder.add(totalAmountPerItem);
            }
        }

        Orders order = orderRepository.save(new Orders(request.getUserId(), totalAmountOnOrder, scratchCardCount));
        return order.getId();
    }
}
