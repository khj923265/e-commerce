package com.example.orderservice.repository;

import com.example.orderservice.domain.Orders;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Orders, Long> {
    Orders findByOrderId(String orderId);
    Iterable<Orders> findByUserId(String userId);
}
