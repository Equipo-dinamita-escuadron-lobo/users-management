package com.usersmanagement.users.adapters.output.persitence;

import com.usersmanagement.users.adapters.output.persitence.entity.UserEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    List<UserEntity> findAll(Specification<UserEntity> spec);

    Optional<UserEntity> findByEmail(String Email);
}
