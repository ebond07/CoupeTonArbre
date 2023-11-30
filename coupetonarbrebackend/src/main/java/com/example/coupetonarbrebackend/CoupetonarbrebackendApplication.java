package com.example.coupetonarbrebackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
;

@SpringBootApplication
public class CoupetonarbrebackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(CoupetonarbrebackendApplication.class, args);
    }

}
