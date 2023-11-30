package com.example.coupetonarbrebackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
;

@SpringBootApplication
//@ComponentScan("com.example.coupetonarbrebackend.User.DataLayer")
public class CoupetonarbrebackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(CoupetonarbrebackendApplication.class, args);
    }

}
