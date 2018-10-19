package com.lhiot.dc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class BasicDataServiceApplication
{
    public static void main(String[] args) {
        SpringApplication.run(BasicDataServiceApplication.class, args);
    }
}