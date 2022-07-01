package com.cts.fse.stockmarket.auth.services;

import java.util.Optional;

import com.cts.fse.stockmarket.auth.model.Users;

public interface IUserService {

	Integer saveEndUser(Users endUser);
	Optional<Users> findByUserName(String userName);
}
