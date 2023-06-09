package com.example.userservice.repository;

import com.example.userservice.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUserId(String userId);

    User findByEmail(String email);
}
