package com.rocket.items;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by xxu on 3/30/2017.
 */
@SpringBootApplication
@EnableScheduling//run @Schedule marked method
@EnableAsync// run @Async marked method
@EnableEurekaClient
public class ItemsApplication {
    public static void main(String[] args) {
        SpringApplication.run(ItemsApplication.class, args);
    }
}
