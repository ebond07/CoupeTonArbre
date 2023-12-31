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
    private String quoteRequestId;
    private String clientId;
    private LocalTime time;
    private Date date;
    private String clientAddress;
    private String description;
    private Service service;
    private Status status;
    private Double price;

    public static String generateUUIDString(){
        return UUID.randomUUID().toString();
    }
}
