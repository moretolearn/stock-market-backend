package com.cts.fse.stockmarket.auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.fse.stockmarket.auth.model.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users,Integer>{

	Optional<Users> findByUserName(String userName);
}
