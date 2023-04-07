package com.coding.orderservice.controller;

import com.coding.orderservice.dto.OrderRequest;
import com.coding.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/order")
@RequiredArgsConstructor
public class orderController {


    private final OrderService orderService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody OrderRequest orderRequest) {
        orderService.placeOrder(orderRequest);
        return "Order is placed Successfully";
    }
}
