package com.cts.fse.stockmarket.auth.service;

import java.util.List;

import com.cts.fse.stockmarket.auth.model.User;

public interface GenericService {

    List<User> findAllUsers();
}