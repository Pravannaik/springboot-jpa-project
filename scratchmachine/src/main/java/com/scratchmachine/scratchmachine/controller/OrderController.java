package com.scratchmachine.scratchmachine.controller;

import com.scratchmachine.scratchmachine.dto.request.OrderRequest;
import com.scratchmachine.scratchmachine.dto.response.Response;
import com.scratchmachine.scratchmachine.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create-order")
    public ResponseEntity<Response> createOrder(@RequestBody @Valid OrderRequest request) {

        String orderRef = orderService.createOrder(request);

        Response response = new Response();
        Map<String, String> mapData = new HashMap<>();
        mapData.put("orderRef", orderRef);
        response.setData(mapData);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
