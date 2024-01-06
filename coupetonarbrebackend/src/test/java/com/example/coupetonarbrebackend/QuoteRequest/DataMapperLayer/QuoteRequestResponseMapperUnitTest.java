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
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class QuoteRequestResponseMapperUnitTest {

    private final QuoteRequestResponseMapper mapper = Mappers.getMapper(QuoteRequestResponseMapper.class);

    private final com.example.coupetonarbrebackend.QuoteRequest.DataLayer.Service hedgeTrimming = com.example.coupetonarbrebackend.QuoteRequest.DataLayer.Service.HedgeTrimming;
    private final com.example.coupetonarbrebackend.QuoteRequest.DataLayer.Status pending = com.example.coupetonarbrebackend.QuoteRequest.DataLayer.Status.PENDING;

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
        assertEquals(quoteRequest.getAddress(), result.getAddress());
        assertEquals(quoteRequest.getService(), result.getService());
        assertEquals(quoteRequest.getStatus(), result.getStatus());
    }

    @Test
    void shouldMapEntityListToResponseModelList() {
        // Given
        QuoteRequest quoteRequest1 = createQuoteRequest("1", "John", "Doe", "10:00", new Date(2023, 12, 30), 100, "Description", "Address");
        QuoteRequest quoteRequest2 = createQuoteRequest("2", "Jane", "Doe", "12:00", new Date(2023, 12, 30), 150, "Another Description", "Another Address");

        // When
        List<QuoteRequestResponseDTO> responseDTOList = mapper.entityListToResponseModelList(Arrays.asList(quoteRequest1, quoteRequest2));

        // Then
        assertEquals(2, responseDTOList.size());

        QuoteRequestResponseDTO responseDTO1 = responseDTOList.get(0);
        assertQuoteRequestResponseDTO(responseDTO1, "1", "John", "Doe", "10:00", new Date(2023, 12, 30), 100, "Description", "Address");

        QuoteRequestResponseDTO responseDTO2 = responseDTOList.get(1);
        assertQuoteRequestResponseDTO(responseDTO2, "2", "Jane", "Doe", "12:00", new Date(2023, 12, 30), 150, "Another Description", "Another Address");
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

    private void assertQuoteRequestResponseDTO(QuoteRequestResponseDTO responseDTO, String quoteRequestId, String clientFirstName, String clientLastName,
                                               String time, Date date, double price, String description, String address) {
        assertEquals(quoteRequestId, responseDTO.getQuoteRequestId());
        assertEquals(clientFirstName, responseDTO.getClientFirstName());
        assertEquals(clientLastName, responseDTO.getClientLastName());
        assertEquals(time, responseDTO.getTime());
        assertEquals(date, responseDTO.getDate());
        assertEquals(price, responseDTO.getPrice());
        assertEquals(description, responseDTO.getDescription());
        assertEquals(address, responseDTO.getAddress());
    }

    private QuoteRequest createQuoteRequest(String quoteRequestId, String clientFirstName, String clientLastName,
                                            String time, Date date, double price, String description, String address) {
        QuoteRequest quoteRequest = new QuoteRequest();
        quoteRequest.setQuoteRequestId(quoteRequestId);
        quoteRequest.setClientFirstName(clientFirstName);
        quoteRequest.setClientLastName(clientLastName);
        quoteRequest.setTime(time);
        quoteRequest.setDate(date);
        quoteRequest.setPrice(price);
        quoteRequest.setDescription(description);
        quoteRequest.setAddress(address);
        return quoteRequest;
    }
}
