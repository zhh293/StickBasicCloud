package com.tmd.user;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableDubbo
public class StickUserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(StickUserServiceApplication.class, args);
    }
}
