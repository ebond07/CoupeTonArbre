package com.example.coupetonarbrebackend.QuoteRequest.DataMapperLayer;


import com.example.coupetonarbrebackend.QuoteRequest.DataLayer.QuoteRequest;
import com.example.coupetonarbrebackend.QuoteRequest.PresentationLayer.QuoteRequestDatesResponseDTO;
import com.example.coupetonarbrebackend.QuoteRequest.PresentationLayer.QuoteRequestResponseDTO;
import com.example.coupetonarbrebackend.QuoteRequest.PresentationLayer.Service;
import com.example.coupetonarbrebackend.QuoteRequest.PresentationLayer.Status;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface QuoteRequestDatesResponseMapper {

    @Mapping(source = "time", target = "time")
    @Mapping(source = "date", target = "date")
    QuoteRequestDatesResponseDTO entityToResponseModel(QuoteRequest quoteRequest);
    List<QuoteRequestDatesResponseDTO> entityListToResponseModelList(List<QuoteRequest> quoteRequests);
}
