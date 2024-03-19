package com.usersmanagement.users.application.ports.input;

import com.usersmanagement.users.domain.model.User;

import java.util.Optional;

public interface ListUsersUseCase {

    Optional<User> getByEmail(String Email);

}