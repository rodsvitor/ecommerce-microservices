package com.ecommerce.auth.domain.repository;

import com.ecommerce.auth.domain.model.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository {

    User save(User user);

    Optional<User> findById(UUID ID);

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

}
