package com.trading;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication(scanBasePackages = {"com.trading"})
@EnableCaching
public class Application {
    public static void main(String args[]) {
        SpringApplication.run(Application.class, args);
    }
}
