package com.cts.fse.stockmarket.command;

import org.springframework.boot.SpringApplication;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.cts.fse.stockmarket.command.repository.CustomRepositoryImpl;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomRepositoryImpl.class)
@EntityScan("com.cts.fse.stockmarket.command.bean")
@CrossOrigin
@EnableEurekaClient
public class CommandServiceApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(CommandServiceApplication.class, args);
    }
}