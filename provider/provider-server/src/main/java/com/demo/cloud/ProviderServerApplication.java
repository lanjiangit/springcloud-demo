package com.demo.cloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.demo.cloud.mapper")
public class ProviderServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProviderServerApplication.class, args);
    }

}
