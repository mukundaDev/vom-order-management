package com.example.vom.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.vom.management.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

}
