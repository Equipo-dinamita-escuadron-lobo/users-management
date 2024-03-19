package com.usersmanagement.users.adapters.input.userprovider;

import com.usersmanagement.users.application.ports.input.UserProviderUseCase;
import com.usersmanagement.users.domain.model.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserProvider implements UserProviderUseCase {
    @Override
    public User getLoggedUser() {
        UsernamePasswordAuthenticationToken tokenAuth = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext()
                .getAuthentication();
        return new User(null, tokenAuth.getPrincipal().toString(),
                null, null, null, null);
    }
}