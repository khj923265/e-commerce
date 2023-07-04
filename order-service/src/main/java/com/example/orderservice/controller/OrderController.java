package com.example.orderservice.controller;

import com.example.orderservice.domain.Orders;
import com.example.orderservice.dto.OrderDto;
import com.example.orderservice.messagequeue.KafkaProducer;
import com.example.orderservice.messagequeue.OrderProducer;
import com.example.orderservice.service.OrderService;
import com.example.orderservice.vo.RequestOrder;
import com.example.orderservice.vo.ResponseOrder;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/order-service")
public class OrderController {

    private final OrderService orderService;
    private final KafkaProducer kafkaProducer;
    private final OrderProducer orderProducer;

    public OrderController(OrderService orderService, KafkaProducer kafkaProducer, OrderProducer orderProducer) {
        this.orderService = orderService;
        this.kafkaProducer = kafkaProducer;
        this.orderProducer = orderProducer;
    }

    @GetMapping("/health_check")
    public String status(HttpServletRequest request) {
        return String.format("It's Working in Order Service On PORT %s", request.getServerPort());
    }

    // http://127.0.0.1/order-service/{user_id}/orders
    @PostMapping("/{userId}/orders")
    public ResponseEntity<ResponseOrder> createOrder(@PathVariable("userId") String userId, @RequestBody RequestOrder orderDetail) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        OrderDto orderDto = mapper.map(orderDetail, OrderDto.class);
        orderDto.setUserId(userId);

        /* JPA */
        ResponseOrder createdOrder = orderService.createOrder(orderDto);

        /* Kafka */
//        orderDto.setOrderId(UUID.randomUUID().toString());
//        orderDto.setTotalPrice(orderDetail.getQuantity() * orderDetail.getUnitPrice());
//        ResponseOrder createdOrder = orderService.createOrder(orderDto);

        /* send this order to the kafka */
//        kafkaProducer.send("example-catalog-topic", orderDto);
//        orderProducer.send("orders", orderDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
    }

    @GetMapping("/{userId}/orders")
    public ResponseEntity<List<ResponseOrder>> createOrder(@PathVariable("userId") String userId) {
        Iterable<Orders> orderList = orderService.getOrdersByUserId(userId);

        List<ResponseOrder> result = new ArrayList<>();
        orderList.forEach(v -> result.add(new ModelMapper().map(v, ResponseOrder.class)));

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
