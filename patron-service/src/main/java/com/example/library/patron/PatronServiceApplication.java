package com.example.library.patron;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PatronServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PatronServiceApplication.class, args);
    }
} 