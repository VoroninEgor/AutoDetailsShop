package com.rmnk12k.controller;

import com.rmnk12k.dto.order.CreateOrderRequest;
import com.rmnk12k.dto.order.ListOrderResponse;
import com.rmnk12k.dto.order.OrderResponse;
import com.rmnk12k.service.OrderService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
@Tag(name = "orders", description = "the orders API")
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/{id}")
    public OrderResponse getOrder(@PathVariable Long id) {
        return orderService.get(id);
    }

    @GetMapping("/users/{id}")
    public ListOrderResponse getOrders(@PathVariable Long id) {
        return orderService.getUsersOrders(id);
    }

    @PostMapping
    public void createOrder(@RequestBody @Valid CreateOrderRequest request) {
        orderService.create(request);
    }
}
