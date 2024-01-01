package com.example.coupetonarbrebackend.QuoteRequest.PresentationLayer;

import com.example.coupetonarbrebackend.QuoteRequest.BusinessLayer.QuoteRequestService;
import com.example.coupetonarbrebackend.QuoteRequest.DataMapperLayer.QuoteRequestRequestMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000",allowCredentials = "true", allowedHeaders = {"xsrf-token", "content-type"})
@RequestMapping("quoteRequests")
public class QuoteRequestController {
    private final QuoteRequestService quoteRequestService;
    private final QuoteRequestRequestMapper quoteRequestRequestMapper;

    public QuoteRequestController(QuoteRequestService quoteRequestService, QuoteRequestRequestMapper quoteRequestRequestMapper) {
        this.quoteRequestService = quoteRequestService;
        this.quoteRequestRequestMapper = quoteRequestRequestMapper;
    }

    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping()
    public ResponseEntity<List<QuoteRequestResponseDTO>> getAllQuoteRequests(){
        return ResponseEntity.ok().body(quoteRequestService.getAllQuoteRequests());
    }
}
