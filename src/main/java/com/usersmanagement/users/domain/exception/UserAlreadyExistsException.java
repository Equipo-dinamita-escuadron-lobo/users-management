package com.usersmanagement.users.domain.exception;

public class UserAlreadyExistsException extends ContAppDomainException {
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}