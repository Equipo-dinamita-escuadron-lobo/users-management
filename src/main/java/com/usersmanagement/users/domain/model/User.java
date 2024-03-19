package com.usersmanagement.users.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class User {
    private Long id;

    private String email;

    private String password;

    private String firstName;

    private String lastName;

    private Set<Role> roles;
}
