package com.Store_api.store.controllers;

import com.Store_api.store.Services.OrderServices;
import com.Store_api.store.dto.Order.OrderDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@AllArgsConstructor
@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderServices orderServices;
    @GetMapping
    public List<OrderDto> getAll()
    {
        return orderServices.getAllOrders();
    }
    @GetMapping("/{orderId}")
    public OrderDto getOrder(@PathVariable("orderId") Long orderId)
    {
        return orderServices.getOrderById(orderId);
    }
}
