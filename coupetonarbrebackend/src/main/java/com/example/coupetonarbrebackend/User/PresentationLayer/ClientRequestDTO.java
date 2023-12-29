package com.example.coupetonarbrebackend.User.PresentationLayer;

import lombok.*;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientRequestDTO {

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address;



    public static String generateUUIDString(){
        return UUID.randomUUID().toString();
    }
}

