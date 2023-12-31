package com.example.coupetonarbrebackend.QuoteRequest.BusinessLayer;

import com.example.coupetonarbrebackend.QuoteRequest.DataLayer.QuoteRequestRepository;
import com.example.coupetonarbrebackend.QuoteRequest.DataMapperLayer.QuoteRequestRequestMapper;
import com.example.coupetonarbrebackend.QuoteRequest.DataMapperLayer.QuoteRequestResponseMapper;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
@Service
public class QuoteRequestServiceImpl implements QuoteRequestService{
    private QuoteRequestRepository quoteRequestRepository;
    private QuoteRequestRequestMapper quoteRequestRequestMapper;
    private QuoteRequestResponseMapper quoteRequestResponseMapper;

    public QuoteRequestServiceImpl(QuoteRequestRepository quoteRequestRepository, QuoteRequestRequestMapper quoteRequestRequestMapper, QuoteRequestResponseMapper quoteRequestResponseMapper) {
        this.quoteRequestRepository = quoteRequestRepository;
        this.quoteRequestRequestMapper = quoteRequestRequestMapper;
        this.quoteRequestResponseMapper = quoteRequestResponseMapper;
    }

}
