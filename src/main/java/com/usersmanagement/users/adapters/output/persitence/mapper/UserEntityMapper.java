package com.usersmanagement.users.adapters.output.persitence.mapper;

import com.usersmanagement.users.adapters.output.persitence.entity.UserEntity;
import com.usersmanagement.users.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserEntityMapper {
    UserEntityMapper INSTANCE = Mappers.getMapper(UserEntityMapper.class);

    UserEntity toEntity(User user);

    User toDomain(UserEntity userEntity);
}