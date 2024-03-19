package com.usersmanagement.users.adapters.input.rest.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CreateUserResponse {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;
}