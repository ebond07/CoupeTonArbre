package com.example.coupetonarbrebackend.User.DataLayer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
@EnableJpaRepositories(basePackages = "com.example.coupetonarbrebackend")
public interface ClientRepository extends JpaRepository<Client, String> {

}

