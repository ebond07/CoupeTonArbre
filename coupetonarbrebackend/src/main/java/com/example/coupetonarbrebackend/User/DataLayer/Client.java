package com.example.coupetonarbrebackend.User.DataLayer;

import lombok.*;
import org.springframework.data.annotation.Id;


@Data
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Client {

    @Id
    public String clientId;
    public String firstName;
    public String lastName;
    public String email;
    public String address;
}
