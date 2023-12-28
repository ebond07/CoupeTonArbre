package com.example.coupetonarbrebackend.User.PresentationLayer;

import lombok.*;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientRequestDTO {

//    private String clientId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address;

//    public ClientRequestDTO(String firstName, String lastName, String email, String phoneNumber, String address) {
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.email = email;
//        this.phoneNumber = phoneNumber;
//        this.address = address;
//    }

    public static String generateUUIDString(){
        return UUID.randomUUID().toString();
    }
}

