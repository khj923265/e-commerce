package com.example.orderservice.service;

import com.example.orderservice.domain.Orders;
import com.example.orderservice.dto.OrderDto;
import com.example.orderservice.repository.OrderRepository;
import com.example.orderservice.vo.ResponseOrder;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService{
    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public ResponseOrder createOrder(OrderDto orderDto) {
        orderDto.setOrderId(UUID.randomUUID().toString());
        orderDto.setTotalPrice(orderDto.getQuantity() * orderDto.getUnitPrice());

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Orders order = mapper.map(orderDto, Orders.class);

        Orders savedOrder = orderRepository.save(order);

        return mapper.map(savedOrder, ResponseOrder.class);
    }

    @Override
    public ResponseOrder getOrderByOrderId(String orderId) {
        Orders order = orderRepository.findByOrderId(orderId);
        return new ModelMapper().map(order, ResponseOrder.class);
    }

    @Override
    public Iterable<Orders> getOrdersByUserId(String userId) {
        return orderRepository.findByUserId(userId);
    }
}
