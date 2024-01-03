package com.example.coupetonarbrebackend.QuoteRequest.BusinessLayer;

import com.example.coupetonarbrebackend.QuoteRequest.DataLayer.QuoteRequest;
import com.example.coupetonarbrebackend.QuoteRequest.DataLayer.QuoteRequestRepository;
import com.example.coupetonarbrebackend.QuoteRequest.DataLayer.Status;
import com.example.coupetonarbrebackend.QuoteRequest.DataMapperLayer.QuoteRequestRequestMapper;
import com.example.coupetonarbrebackend.QuoteRequest.DataMapperLayer.QuoteRequestResponseMapper;
import com.example.coupetonarbrebackend.QuoteRequest.PresentationLayer.QuoteRequestRequestDTO;
import com.example.coupetonarbrebackend.QuoteRequest.PresentationLayer.QuoteRequestResponseDTO;
import com.example.coupetonarbrebackend.User.BusinessLayer.ClientService;
import com.example.coupetonarbrebackend.User.PresentationLayer.ClientResponseDTO;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuoteRequestServiceImpl implements QuoteRequestService{
    private QuoteRequestRepository quoteRequestRepository;
    private QuoteRequestRequestMapper quoteRequestRequestMapper;
    private QuoteRequestResponseMapper quoteRequestResponseMapper;
    private QuoteRequestRequestDTO quoteRequestRequestDTO;

    private ClientService clientService;

    public QuoteRequestServiceImpl(QuoteRequestRepository quoteRequestRepository, QuoteRequestRequestMapper quoteRequestRequestMapper, QuoteRequestResponseMapper quoteRequestResponseMapper, ClientService clientService){
        this.quoteRequestRepository = quoteRequestRepository;
        this.quoteRequestRequestMapper = quoteRequestRequestMapper;
        this.quoteRequestResponseMapper = quoteRequestResponseMapper;
        this.clientService = clientService;
    }

    @Override
    public List<QuoteRequestResponseDTO> getAllQuoteRequests() {
        return quoteRequestResponseMapper.entityListToResponseModelList(quoteRequestRepository.findAll());
    }

    @Override
    public QuoteRequestResponseDTO adminCreateQuoteRequest(QuoteRequestRequestDTO quoteRequestRequestDTO) {

        QuoteRequest quoteRequest = quoteRequestRequestMapper.requestModelToEntity(quoteRequestRequestDTO);
        quoteRequest.setQuoteRequestId(quoteRequestRequestDTO.generateUUIDString());
        quoteRequest.setStatus(Status.QUOTE_SENT);

        return quoteRequestResponseMapper.entityToResponseModel(quoteRequestRepository.save(quoteRequest));
    }

    @Override
    public QuoteRequestResponseDTO clientCreateQuoteRequest(QuoteRequestRequestDTO quoteRequestRequestDTO, String clientId) {

        QuoteRequest quoteRequest = quoteRequestRequestMapper.requestModelToEntity(quoteRequestRequestDTO);
        quoteRequest.setQuoteRequestId(quoteRequestRequestDTO.generateUUIDString());

        ClientResponseDTO clientResponseDTO = clientService.getClientById(clientId);
        quoteRequest.setClientId(clientResponseDTO.getClientId());
        quoteRequest.setClientFirstName(clientResponseDTO.getFirstName());
        quoteRequest.setClientLastName(clientResponseDTO.getLastName());
        quoteRequest.setStatus(Status.QUOTE_SENT);


        return quoteRequestResponseMapper.entityToResponseModel(quoteRequestRepository.save(quoteRequest));
    }
}

