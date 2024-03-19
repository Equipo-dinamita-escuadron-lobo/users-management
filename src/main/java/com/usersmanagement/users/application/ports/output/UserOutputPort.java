package com.usersmanagement.users.application.ports.output;

import com.usersmanagement.users.adapters.output.persitence.entity.UserEntity;
import com.usersmanagement.users.domain.model.User;

import java.util.Optional;

public interface UserOutputPort {
    User createUser(User user);

    Optional<User> getUser(String email);

    UserEntity save(User user);
}
