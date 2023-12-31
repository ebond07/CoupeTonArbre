package com.example.coupetonarbrebackend.QuoteRequest.PresentationLayer;

import com.example.coupetonarbrebackend.QuoteRequest.BusinessLayer.QuoteRequestService;
import com.example.coupetonarbrebackend.QuoteRequest.DataMapperLayer.QuoteRequestRequestMapper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
