package com.usersmanagement.users.adapters.input.rest.mapper;


import com.usersmanagement.users.adapters.input.rest.data.CreateUserRequest;
import com.usersmanagement.users.adapters.input.rest.data.CreateUserResponse;
import com.usersmanagement.users.domain.model.Role;
import com.usersmanagement.users.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RestMapper {

    RestMapper INSTANCE = Mappers.getMapper(RestMapper.class);

    @Mapping(target = "roles",
            expression = "java(java.util.Set.of(toEnum(request.getRole())))")
    User toDomain(CreateUserRequest request);

    CreateUserResponse toResponse(User user);


    default Role toEnum(String role){
        return Role.valueOf(role);
    }
}