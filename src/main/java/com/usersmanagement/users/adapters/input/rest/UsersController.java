package com.usersmanagement.users.adapters.input.rest;

import com.usersmanagement.users.adapters.input.rest.data.CreateUserRequest;
import com.usersmanagement.users.adapters.input.rest.data.CreateUserResponse;
import com.usersmanagement.users.adapters.input.rest.mapper.RestMapper;
import com.usersmanagement.users.application.ports.input.CreateUserUseCase;
import com.usersmanagement.users.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RequestMapping(value = "/user")
@RestController
public class UsersController {

    private final CreateUserUseCase createUser;



    @Autowired
    public UsersController(CreateUserUseCase createUser) {
        this.createUser = createUser;
    }

    @PostMapping("/create-user")
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody CreateUserRequest request){
        User user = RestMapper.INSTANCE.toDomain(request);
        user = this.createUser.createUser(user);
        CreateUserResponse response = RestMapper.INSTANCE.toResponse(user);
        return ResponseEntity.created(URI.create("/users/create-user")).body(response);
    }



}