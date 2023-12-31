package com.example.coupetonarbrebackend.QuoteRequest.BusinessLayer;

import com.example.coupetonarbrebackend.QuoteRequest.DataLayer.QuoteRequestRepository;
import com.example.coupetonarbrebackend.QuoteRequest.DataMapperLayer.QuoteRequestRequestMapper;
import com.example.coupetonarbrebackend.QuoteRequest.DataMapperLayer.QuoteRequestResponseMapper;
import com.example.coupetonarbrebackend.QuoteRequest.PresentationLayer.QuoteRequestRequestDTO;
import com.example.coupetonarbrebackend.QuoteRequest.PresentationLayer.QuoteRequestResponseDTO;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuoteRequestServiceImpl implements QuoteRequestService{
    private QuoteRequestRepository quoteRequestRepository;
    private QuoteRequestRequestMapper quoteRequestRequestMapper;
    private QuoteRequestResponseMapper quoteRequestResponseMapper;
    private QuoteRequestRequestDTO quoteRequestRequestDTO;

    public QuoteRequestServiceImpl(QuoteRequestRepository quoteRequestRepository, QuoteRequestRequestMapper quoteRequestRequestMapper, QuoteRequestResponseMapper quoteRequestResponseMapper) {
        this.quoteRequestRepository = quoteRequestRepository;
        this.quoteRequestRequestMapper = quoteRequestRequestMapper;
        this.quoteRequestResponseMapper = quoteRequestResponseMapper;
    }

    @Override
    public List<QuoteRequestResponseDTO> getAllQuoteRequests() {
        return quoteRequestResponseMapper.entityListToResponseModelList(quoteRequestRepository.findAll());
    }
}
