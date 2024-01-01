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
    @GeneratedValue(strategy = GenerationType.UUID)
    public String quoteRequestId;
    public String clientId;
    public String clientFirstName;
    public String clientLastName;
    public String time;
    public Date date;
    public Double price;
    public String description;
    @Enumerated(EnumType.STRING)
    @Column(name = "service")
    public Service service;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    public Status status;
}
