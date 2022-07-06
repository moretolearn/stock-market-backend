package com.cts.fse.stockmarket.auth.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.fse.stockmarket.auth.model.User;
import com.cts.fse.stockmarket.auth.repository.UserRepository;
import com.cts.fse.stockmarket.auth.service.GenericService;

import java.util.List;
@Service
public class GenericServiceImpl implements GenericService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAllUsers() {
        return (List<User>)userRepository.findAll();
    }

}