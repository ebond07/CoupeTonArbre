package com.example.coupetonarbrebackend.QuoteRequest.DataMapperLayer;

import com.example.coupetonarbrebackend.QuoteRequest.DataLayer.QuoteRequest;
import com.example.coupetonarbrebackend.QuoteRequest.PresentationLayer.QuoteRequestResponseDTO;
import com.example.coupetonarbrebackend.QuoteRequest.PresentationLayer.Service;
import com.example.coupetonarbrebackend.QuoteRequest.PresentationLayer.Status;
import lombok.Generated;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface QuoteRequestResponseMapper {
    @Mapping(source = "quoteRequestId", target = "quoteRequestId")
    @Mapping(source = "clientId", target = "clientId")
    @Mapping(source = "clientFirstName", target = "clientFirstName")
    @Mapping(source = "clientLastName", target = "clientLastName")
    @Mapping(source = "time", target = "time")
    @Mapping(source = "date", target = "date")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "service", target = "service", qualifiedByName = "mapServiceEnum")
    @Mapping(source = "status", target = "status", qualifiedByName = "mapStatusEnum")

    @Named("mapStatusEnum")
    default Status mapStatusEnum(String status) {
        try {
            return Status.valueOf(status);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    @Named("mapServiceEnum")
    default Service mapServiceEnum(String service) {
        try {
            return Service.valueOf(service);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
    QuoteRequestResponseDTO entityToResponseModel(QuoteRequest quoteRequest);
    List<QuoteRequestResponseDTO> entityListToResponseModelList(List<QuoteRequest> quoteRequests);
}
