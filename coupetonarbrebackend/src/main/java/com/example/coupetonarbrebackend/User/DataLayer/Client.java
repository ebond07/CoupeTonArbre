package com.example.coupetonarbrebackend.User.DataLayer;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.*;
//import org.springframework.data.annotation.Id;
import jakarta.persistence.*;


@Data
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    public String clientId;
    public String firstName;
    public String lastName;
    public String email;
    public String address;


}
