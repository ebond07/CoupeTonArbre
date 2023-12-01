package com.example.coupetonarbrebackend.User.DataLayer;

import com.example.coupetonarbrebackend.User.DataLayer.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

}

