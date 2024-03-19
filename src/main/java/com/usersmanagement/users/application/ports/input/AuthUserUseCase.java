package com.usersmanagement.users.application.ports.input;

import java.util.Optional;

public interface AuthUserUseCase {
    Optional<String> authUser(String email, String password);
}