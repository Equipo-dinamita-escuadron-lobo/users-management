package com.usersmanagement.users.adapters.output.persitence;

import com.usersmanagement.users.adapters.output.persitence.entity.UserEntity;
import com.usersmanagement.users.adapters.output.persitence.mapper.UserEntityMapper;
import com.usersmanagement.users.application.ports.output.UserOutputPort;
import com.usersmanagement.users.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class UserPersistenceAdapter implements UserOutputPort {


    private final UserRepository userRepository;

    @Autowired
    public UserPersistenceAdapter (UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public User createUser(User user) {
        UserEntity userEntity = UserEntityMapper.INSTANCE.toEntity(user);
        userEntity = this.userRepository.save(userEntity);
        return UserEntityMapper.INSTANCE.toDomain(userEntity);    }

    @Override
    public Optional<User> getUser(String email) {
        return this.userRepository.findByEmail(email).map(UserEntityMapper.INSTANCE::toDomain);
    }

    @Override
    public UserEntity save(User user) {
        UserEntity userEntity = UserEntityMapper.INSTANCE.toEntity(user);
        return this.userRepository.save(userEntity);    }
}
