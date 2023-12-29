package com.example.coupetonarbrebackend.QuoteRequest.DataLayer;

import jakarta.persistence.*;
import lombok.*;

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
}
