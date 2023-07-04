package com.example.userservice.service;

import com.example.userservice.client.OrderServiceClient;
import com.example.userservice.domain.User;
import com.example.userservice.dto.UserDto;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.vo.ResponseOrder;
import com.example.userservice.vo.ResponseUser;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final Environment env;
    private final RestTemplate restTemplate;
    private final OrderServiceClient orderServiceClient;

    private final CircuitBreakerFactory circuitBreakerFactory;

    public UserServiceImpl(UserRepository userRepository, @Lazy BCryptPasswordEncoder passwordEncoder, Environment env, RestTemplate restTemplate, OrderServiceClient orderServiceClient, CircuitBreakerFactory circuitBreakerFactory) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.env = env;
        this.restTemplate = restTemplate;
        this.orderServiceClient = orderServiceClient;
        this.circuitBreakerFactory = circuitBreakerFactory;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);

        if (user == null) {
            throw new UsernameNotFoundException("username not found");
        }

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getEncryptedPwd(),
                true, true, true, true, new ArrayList<>());
    }

    @Override
    public ResponseUser createUser(UserDto userDto) {
        userDto.setUserId(UUID.randomUUID().toString());

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        User user = mapper.map(userDto, User.class);
        user.setEncryptedPwd(passwordEncoder.encode(userDto.getPwd()));

        User savedUser = userRepository.save(user);
        return mapper.map(savedUser, ResponseUser.class);
    }

    @Override
    public ResponseUser getUserByUserId(String userId) {
        User user = userRepository.findByUserId(userId);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        UserDto userDto = new ModelMapper().map(user, UserDto.class);

        /* Using a RestTemplate */
//        String orderUrl = String.format(env.getProperty("order_service.url"), userId);
//        ResponseEntity<List<ResponseOrder>> orderListResponse = restTemplate.exchange(orderUrl, HttpMethod.GET, null,
//                new ParameterizedTypeReference<List<ResponseOrder>>() {
//
//        });
//        List<ResponseOrder> orderList = orderListResponse.getBody();

        /* Using a feign client */
        List<ResponseOrder> orderList = null;
        /* Feign Exception */
//        try {
//            orderList = orderServiceClient.getOrders(userId);
//        } catch (FeignException e) {
//            log.error(e.getMessage());
//        }

        /* Error Decoder */
//        orderList = orderServiceClient.getOrders(userId);

        /* CircuitBreaker */
        CircuitBreaker circuitbreaker = circuitBreakerFactory.create("circuitbreaker");
        orderList = circuitbreaker.run(() -> orderServiceClient.getOrders(userId),
                throwable -> new ArrayList<>());
        userDto.setOrders(orderList);
        return new ModelMapper().map(userDto, ResponseUser.class);
    }

    @Override
    public Iterable<User> getUserByAll() {
        return userRepository.findAll();
    }

    @Override
    public UserDto getUserDetailByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("email not found");
        }
        return new ModelMapper().map(user, UserDto.class);
    }
}