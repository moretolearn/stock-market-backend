package com.cts.fse.stockmarket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.cts.fse.stockmarket.commond.bean")
public class QueryServiceApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(QueryServiceApplication.class, args);
    }
}