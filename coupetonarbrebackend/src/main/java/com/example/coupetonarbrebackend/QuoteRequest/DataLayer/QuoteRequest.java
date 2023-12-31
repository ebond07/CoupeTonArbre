package com.example.coupetonarbrebackend.QuoteRequest.DataLayer;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;
import java.util.Date;

@Data
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity

@Table(name = "quote_requests")
public class QuoteRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    public String quoteRequestId;
    public String clientId;
    public String clientFirstName;
    public String clientLastName;
    public LocalTime time;
    public Date date;
    public Double price;
    public String description;
    public Service service;
    public Status status;
}
