package com.usersmanagement.users.domain.service;

import com.usersmanagement.users.application.ports.input.CreateUserUseCase;
import com.usersmanagement.users.application.ports.input.ListUsersUseCase;
import com.usersmanagement.users.application.ports.output.UserOutputPort;
import com.usersmanagement.users.domain.exception.UserAlreadyExistsException;
import com.usersmanagement.users.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserService implements CreateUserUseCase, ListUsersUseCase {
    private final UserOutputPort outputPort;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService (UserOutputPort outputPort, PasswordEncoder passwordEncoder) {
        this.outputPort = outputPort;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User createUser(User user) {
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        if(this.outputPort.getUser(user.getEmail()).isPresent()){
            throw new UserAlreadyExistsException("User already exists");
        }
        return this.outputPort.createUser(user);
    }

    @Override
    public Optional<User> getByEmail(String email) {
        return outputPort.getUser(email);
    }

}
