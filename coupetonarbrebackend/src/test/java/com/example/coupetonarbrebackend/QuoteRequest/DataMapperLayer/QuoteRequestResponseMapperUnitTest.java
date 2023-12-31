package com.example.coupetonarbrebackend.QuoteRequest.DataMapperLayer;

import com.example.coupetonarbrebackend.QuoteRequest.DataLayer.QuoteRequest;
import com.example.coupetonarbrebackend.QuoteRequest.PresentationLayer.QuoteRequestResponseDTO;
import com.example.coupetonarbrebackend.QuoteRequest.PresentationLayer.Service;
import com.example.coupetonarbrebackend.QuoteRequest.PresentationLayer.Status;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.mapstruct.factory.Mappers;

import java.time.LocalTime;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class QuoteRequestResponseMapperUnitTest {

    private final QuoteRequestResponseMapper mapper = Mappers.getMapper(QuoteRequestResponseMapper.class);

    @Test
    void testEntityToResponseModelMapping() {
        QuoteRequest quoteRequest = new QuoteRequest();

        QuoteRequestResponseDTO result = mapper.entityToResponseModel(quoteRequest);

        assertEquals(quoteRequest.getQuoteRequestId(), result.getQuoteRequestId());
        assertEquals(quoteRequest.getClientId(), result.getClientId());
        assertEquals(quoteRequest.getClientFirstName(), result.getClientFirstName());
        assertEquals(quoteRequest.getClientLastName(), result.getClientLastName());
        assertEquals(quoteRequest.getTime(), result.getTime());
        assertEquals(quoteRequest.getDate(), result.getDate());
        assertEquals(quoteRequest.getPrice(), result.getPrice());
        assertEquals(quoteRequest.getDescription(), result.getDescription());
        assertEquals(quoteRequest.getService(), result.getService());
        assertEquals(quoteRequest.getStatus(), result.getStatus());
    }

    @Test
    void shouldMapStatusEnum() {
        // Arrange
        String validStatus = "PENDING";
        String invalidStatus = "INVALID_STATUS";

        // Act
        Status mappedValidStatus = mapper.mapStatusEnum(validStatus);
        Status mappedInvalidStatus = mapper.mapStatusEnum(invalidStatus);

        // Assert
        assertEquals(Status.PENDING, mappedValidStatus);
        assertNull(mappedInvalidStatus);
    }

    @Test
    void testMapServiceEnum_Success() {
        // Arrange
        String validServiceString = "TreeTrimming";

        // Act
        Service result = mapper.mapServiceEnum(validServiceString);

        // Assert
        assertEquals(Service.TreeTrimming, result);
    }

    @Test
    void testMapServiceEnum_InvalidValue() {
        // Arrange
        String invalidServiceString = "InvalidService";

        // Act
        Service result = mapper.mapServiceEnum(invalidServiceString);

        // Assert
        assertNull(result);
    }

    @ParameterizedTest
    @EnumSource(Status.class)
    void shouldMapAllStatusEnums(Status status) {
        // Act
        Status mappedStatus = mapper.mapStatusEnum(status.name());

        // Assert
        assertEquals(status, mappedStatus);
    }

    @ParameterizedTest
    @EnumSource(Service.class)
    void shouldMapAllServiceEnums(Service service) {
        // Act
        Service mappedService = mapper.mapServiceEnum(service.name());

        // Assert
        assertEquals(service, mappedService);
    }
}
