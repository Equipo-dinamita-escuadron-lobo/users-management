package com.usersmanagement.users.adapters.input.rest.data;


import lombok.Getter;

import java.util.List;

@Getter
public class ApiErrorResponse {

    private final List<String> messages;

    private ApiErrorResponse(String ...messages){
        this.messages = List.of(messages);
    }

    public static ApiErrorResponse withMessages(String ...messages){
        return new ApiErrorResponse(messages);
    }
}