package com.example.coupetonarbrebackend.configuration.security.models;

import lombok.*;

import java.util.Dictionary;
import java.util.HashMap;

@EqualsAndHashCode(callSuper=false)
@Data
@AllArgsConstructor
@Builder
@Generated
public class UserInfoResponse {
    private String username;
    private String email;
    private String picture;
    private HashMap<String, String> user_metadata;

}