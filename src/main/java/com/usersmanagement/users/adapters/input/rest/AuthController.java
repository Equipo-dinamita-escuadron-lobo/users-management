package com.usersmanagement.users.adapters.input.rest;

import com.usersmanagement.users.adapters.input.rest.data.AuthUserRequest;
import com.usersmanagement.users.application.ports.input.AuthUserUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    private final AuthUserUseCase authUser;

    @Autowired
    public AuthController(AuthUserUseCase authUser){
        this.authUser = authUser;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthUserRequest request){
        Optional<String> token = this.authUser.authUser(request.getEmail(), request.getPassword());

        return token.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());

    }

}