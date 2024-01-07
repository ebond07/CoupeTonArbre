package com.example.coupetonarbrebackend.QuoteRequest.PresentationLayer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class QuoteRequestRequestDTO {
    private String clientId;
    private String clientFirstName;
    private String clientLastName;
    private String time;
    private Date date;
    private Double price;
    private String description;
    private String address;
    private Service service;
    private Status status;

    public static String generateUUIDString(){
        return UUID.randomUUID().toString();
    }
}
