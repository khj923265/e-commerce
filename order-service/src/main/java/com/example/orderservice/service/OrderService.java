package com.example.orderservice.service;

import com.example.orderservice.domain.Orders;
import com.example.orderservice.dto.OrderDto;
import com.example.orderservice.vo.ResponseOrder;

public interface OrderService {
    ResponseOrder createOrder(OrderDto orderDto);
    ResponseOrder getOrderByOrderId(String orderId);
    Iterable<Orders> getOrdersByUserId(String userId);
}
