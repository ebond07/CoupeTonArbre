package com.example.coupetonarbrebackend.configuration.security.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Generated;

@EqualsAndHashCode(callSuper=false)
@Data
@AllArgsConstructor
@Generated
public class UserRequest {
    private String username;
    private String email;
    private String password;
}