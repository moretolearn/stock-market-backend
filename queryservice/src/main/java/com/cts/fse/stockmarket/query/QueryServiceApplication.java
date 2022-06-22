package com.cts.fse.stockmarket.query;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;

import com.mongodb.MongoClientOptions;


@SpringBootApplication
@EntityScan("com.cts.fse.stockmarket.query.bean")
public class QueryServiceApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(QueryServiceApplication.class, args);
    }
    
    @Bean
    public MongoClientOptions mongoOptions() {
        return MongoClientOptions.builder()
        		.socketTimeout(2000)
        		.serverSelectionTimeout(2000)
        		.connectTimeout(2000)
//        		.requiredReplicaSetName()
        	    .maxConnectionIdleTime (2000)
        	    .connectionsPerHost(2000)
        	    .cursorFinalizerEnabled(false)
        		.build();
    }
}