package com.example.coupetonarbrebackend.User.PresentationLayer;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientResponseDTO {

    private String clientId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address;
}
