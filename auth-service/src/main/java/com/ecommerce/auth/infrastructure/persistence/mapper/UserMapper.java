package com.ecommerce.auth.infrastructure.persistence.mapper;

import com.ecommerce.auth.domain.model.User;
import com.ecommerce.auth.infrastructure.persistence.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

  User toDomain(UserEntity entity);

  UserEntity toEntity(User user);

}
