package com.example.coupetonarbrebackend.User.PresentationLayer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientRequestDTO {

    private String firstName;
    private String lastName;
    private String email;
    private String address;
}
