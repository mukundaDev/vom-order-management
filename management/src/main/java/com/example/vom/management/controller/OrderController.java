package com.example.vom.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.vom.management.model.Order;
import com.example.vom.management.service.OrderService;


@RestController
@RequestMapping("/orders")
public class OrderController {

	    @Autowired
	    private OrderService orderService;

	    @PostMapping
	    public Order createOrder(
	            @RequestParam String customerId,
	            @RequestParam String serviceType,
	            @RequestParam String provider) {
	        return orderService.createOrder(customerId, serviceType, provider);
	    }

	    @PutMapping("/{orderId}/validate")
	    public Order validateOrder(@PathVariable Long orderId) {
	        return orderService.validateOrder(orderId);
	    }

	    @PutMapping("/{orderId}/submit")
	    public Order submitOrder(@PathVariable Long orderId) {
	        return orderService.submitOrder(orderId);
	    }

	    @GetMapping("/{orderId}/status")
	    public String trackOrderStatus(@PathVariable Long orderId) {
	        return orderService.trackOrderStatus(orderId);
	    }
}
