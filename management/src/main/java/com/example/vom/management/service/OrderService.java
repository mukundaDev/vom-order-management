package com.example.vom.management.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.vom.management.model.Order;
import com.example.vom.management.repository.OrderRepository;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	//create a new order
	public Order createOrder(String customerId, String serviceType, String provider) {
		Order order = new Order();
		order.setCustomerId(customerId);
		order.setServiceType(serviceType);
		order.setProvider(provider);
		order.setStatus("CREATED");
		order.setCreatedAt(LocalDateTime.now());
		
		return orderRepository.save(order);
	}
	
	//validate order
	public Order validateOrder(Long orderId) {
		Order order = orderRepository.findById(orderId)
				.orElseThrow(()-> new RuntimeException("Order not found"));
		if(!order.getStatus().equals("CREATED")) {
			throw new RuntimeException("Order cannot be validated in its current state");
		}
		 order.setStatus("VALIDATED");
	        order.setUpdatedAt(LocalDateTime.now());
	        return orderRepository.save(order);
	}
	
	 // Submit the order to the provider
    public Order submitOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        if (!order.getStatus().equals("VALIDATED")) {
            throw new RuntimeException("Order cannot be submitted in its current state");
        }
        // Simulate submission to provider (e.g., via API call)
        boolean submissionSuccess = submitToProvider(order);
        if (submissionSuccess) {
            order.setStatus("SUBMITTED");
        } else {
            order.setStatus("FAILED");
        }
        order.setUpdatedAt(LocalDateTime.now());
        return orderRepository.save(order);
    }

    // Track the order status
    public String trackOrderStatus(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        return order.getStatus();
    }
 // Simulate submission to provider (e.g., COX, AT&T)
    private boolean submitToProvider(Order order) {
        // Placeholder for actual provider API integration
        System.out.println("Submitting order to provider: " + order.getProvider());
        return true; // Assume submission is successful for this example
    }
	

}
