package com.usersmanagement.users.domain.service;

import com.usersmanagement.users.application.ports.input.AuthUserUseCase;
import com.usersmanagement.users.application.ports.output.UserOutputPort;
import com.usersmanagement.users.domain.model.User;
import com.usersmanagement.users.domain.model.Role;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AuthService implements AuthUserUseCase {

    private final PasswordEncoder passwordEncoder;

    private final Environment environment;

    private final UserOutputPort outputPort;

    @Autowired
    public AuthService(PasswordEncoder passwordEncoder, Environment environment, UserOutputPort outputPort) {
        this.passwordEncoder = passwordEncoder;
        this.environment = environment;
        this.outputPort = outputPort;
    }

    @Override
    @Transactional
    public Optional<String> authUser(String email, String password) {
        // implementar get user by email
        Optional<User> user = this.outputPort.getUser(email);
        if(user.isEmpty() || !this.passwordEncoder.matches(password, user.get().getPassword())){
            return Optional.empty();
        }
        return Optional.of(this.buildJwtToken(user.get()));
    }

    private String buildJwtToken(User usr){
        Map<String, Object> claims = new HashMap<>();
        Collection<String> roles = usr.getRoles().stream().map(Role::toString).collect(Collectors.toList());
        claims.put("roles", roles);
        claims.put("user_id", usr.getId());
        String jwtKey = environment.getProperty("jwt.secret.key");
        return Jwts.builder()
                .setSubject(usr.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 day
                .signWith(SignatureAlgorithm.HS512, jwtKey)
                .addClaims(claims)
                .compact();
    }

}