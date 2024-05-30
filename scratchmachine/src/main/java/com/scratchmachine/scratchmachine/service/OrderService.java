package com.scratchmachine.scratchmachine.service;

import com.scratchmachine.scratchmachine.dto.request.OrderRequest;

public interface OrderService {
    String createOrder(OrderRequest request);
}
