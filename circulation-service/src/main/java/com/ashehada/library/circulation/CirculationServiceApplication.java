package com.ashehada.library.circulation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CirculationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CirculationServiceApplication.class, args);
    }
} 