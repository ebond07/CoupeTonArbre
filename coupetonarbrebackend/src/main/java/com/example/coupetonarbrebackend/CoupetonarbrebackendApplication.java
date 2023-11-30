package com.example.coupetonarbrebackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.example.coupetonarbrebackend")
@EnableJpaRepositories("com.example.coupetonarbrebackend")
public class CoupetonarbrebackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoupetonarbrebackendApplication.class, args);
    }

}
