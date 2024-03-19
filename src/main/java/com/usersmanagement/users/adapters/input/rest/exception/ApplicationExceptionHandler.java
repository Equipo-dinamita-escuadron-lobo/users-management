package com.usersmanagement.users.adapters.input.rest.exception;

import com.usersmanagement.users.adapters.input.rest.constants.ErrorMessages;
import com.usersmanagement.users.adapters.input.rest.data.ApiErrorResponse;
import com.usersmanagement.users.domain.exception.ContAppDomainException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationExceptionHandler {
    private final Logger LOGGER = LoggerFactory.getLogger(ApplicationExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGenericException(Exception ex)  {
        if(ex instanceof AccessDeniedException) return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        LOGGER.error(ex.getMessage(), ex);

        return ResponseEntity.internalServerError().body(ApiErrorResponse.withMessages(ErrorMessages.UNEXPECTED_ERROR));
    }

    @ExceptionHandler(ContAppDomainException.class)
    public ResponseEntity<Object> handleDomainException(ContAppDomainException ex)  {
        LOGGER.error(ex.getMessage(), ex);
        return ResponseEntity.badRequest().body(ApiErrorResponse.withMessages(ex.getMessage()));
    }


}
