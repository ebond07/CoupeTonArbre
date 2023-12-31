package com.example.coupetonarbrebackend.QuoteRequest.DataMapperLayer;

import com.example.coupetonarbrebackend.QuoteRequest.DataLayer.QuoteRequest;
import com.example.coupetonarbrebackend.QuoteRequest.PresentationLayer.QuoteRequestResponseDTO;
import com.example.coupetonarbrebackend.QuoteRequest.PresentationLayer.Status;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface QuoteRequestResponseMapper {
    @Mapping(source = "quoteRequestId", target = "quoteRequestId")
    @Mapping(source = "clientId", target = "clientId")
    @Mapping(source = "client.firstName", target = "clientFirstName")
    @Mapping(source = "client.lastName", target = "clientLastName")
    @Mapping(source = "client.address", target = "clientAddress")
    @Mapping(source = "client.email", target = "clientEmail")
    @Mapping(source = "time", target = "time")
    @Mapping(source = "date", target = "date")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "service", target = "service")
    @Mapping(source = "status", target = "status", qualifiedByName = "mapStatusEnum")

    @Named("mapStatusEnum")
    default Status mapStatusEnum(String status) {
        try {
            return Status.valueOf(status);
        } catch (IllegalArgumentException e) {
            // Handle the case where the status is not recognized
            return null; // or throw an exception, or provide a default value
        }
    }
    QuoteRequestResponseDTO entityToResponseModel(QuoteRequest quoteRequest);
}
