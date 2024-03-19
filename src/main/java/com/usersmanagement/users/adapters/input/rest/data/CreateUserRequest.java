package com.usersmanagement.users.adapters.input.rest.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
public class CreateUserRequest {

    @Getter
    private String email;

    @Getter
    private String password;

    @Getter
    private String firstName;

    @Getter
    private String lastName;

    @Getter
    private String role;


}