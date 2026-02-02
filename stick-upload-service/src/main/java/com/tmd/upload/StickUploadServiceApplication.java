package com.tmd.upload;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableDubbo
public class StickUploadServiceApplication {


    public static void main(String[] args) {
        SpringApplication.run(StickUploadServiceApplication.class, args);
    }
}
