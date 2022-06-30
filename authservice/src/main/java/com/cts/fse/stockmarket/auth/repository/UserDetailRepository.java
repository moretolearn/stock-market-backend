package com.cts.fse.stockmarket.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.fse.stockmarket.auth.model.User;

import java.util.Optional;

public interface UserDetailRepository extends JpaRepository<User,Integer> {


    Optional<User> findByUsername(String name);

}