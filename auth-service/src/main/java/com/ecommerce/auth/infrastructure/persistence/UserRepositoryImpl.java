package com.ecommerce.auth.infrastructure.persistence;

import com.ecommerce.auth.domain.model.User;
import com.ecommerce.auth.domain.repository.UserRepository;
import com.ecommerce.auth.infrastructure.persistence.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class UserRepositoryImpl implements UserRepository {

  private final SpringDataUserRepository jpaRepository;
  private final UserMapper userMapper;

  @Override
  public User save(User user) {

    UserEntity entity = userMapper.toEntity(user);

    UserEntity saved = jpaRepository.save(entity);

    return userMapper.toDomain(saved);

  }

  @Override
  public Optional<User> findById(UUID id) {
    return jpaRepository.findById(id)
        .map(userMapper::toDomain);
  }

  @Override
  public Optional<User> findByUsername(String username) {
    return jpaRepository.findByUsername(username)
        .map(userMapper::toDomain);
  }

  @Override
  public Optional<User> findByEmail(String email) {
    return jpaRepository.findByEmail(email)
        .map(userMapper::toDomain);
  }
}
