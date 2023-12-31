package com.example.coupetonarbrebackend.QuoteRequest.PresentationLayer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuoteRequestResponseDTO {
    private String quoteRequestId;
    private String clientId;
    private String clientFirstName;
    private String clientLastName;
    private String clientAddress;
    private String clientEmail;
    private Date date;
    private LocalTime time;
    private Double price;
    private String description;
    private Service service;
    private Status status;
}
