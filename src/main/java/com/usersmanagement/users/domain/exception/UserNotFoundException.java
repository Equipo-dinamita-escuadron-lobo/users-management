package com.usersmanagement.users.domain.exception;

public class UserNotFoundException extends ContAppDomainException {
    public UserNotFoundException(String message) {
        super(message);
    }
}