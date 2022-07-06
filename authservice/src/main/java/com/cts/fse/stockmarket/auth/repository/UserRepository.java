package com.cts.fse.stockmarket.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.fse.stockmarket.auth.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByName(String userName);
    boolean existsByName(String userName);
}
