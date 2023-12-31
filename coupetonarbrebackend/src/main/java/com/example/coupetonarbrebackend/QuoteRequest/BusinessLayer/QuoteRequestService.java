package com.example.coupetonarbrebackend.QuoteRequest.BusinessLayer;

import com.example.coupetonarbrebackend.QuoteRequest.PresentationLayer.QuoteRequestResponseDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface QuoteRequestService {
    List<QuoteRequestResponseDTO> getAllQuoteRequests();
}
