package com.example.coupetonarbrebackend.QuoteRequest.PresentationLayer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuoteRequestDatesResponseDTO {

    private String time;
    private Date date;

}