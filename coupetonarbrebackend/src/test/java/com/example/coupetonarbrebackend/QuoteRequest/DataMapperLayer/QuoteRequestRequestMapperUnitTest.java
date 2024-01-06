package com.example.coupetonarbrebackend.QuoteRequest.DataMapperLayer;

import com.example.coupetonarbrebackend.QuoteRequest.DataLayer.QuoteRequest;
import com.example.coupetonarbrebackend.QuoteRequest.PresentationLayer.QuoteRequestRequestDTO;
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

class QuoteRequestRequestMapperUnitTest {

    private final QuoteRequestRequestMapper mapper = Mappers.getMapper(QuoteRequestRequestMapper.class);

    @Test
    void shouldMapDtoToEntity() {
        // Arrange
        QuoteRequestRequestDTO dto = new QuoteRequestRequestDTO();
        dto.setClientId("123456789");
        dto.setClientFirstName("John");
        dto.setClientLastName("Doe");
        dto.setTime("9:00");
        dto.setDate(new Date());
        dto.setPrice(100.0);
        dto.setDescription("Request description");
        dto.setAddress("1234 Street");
        dto.setService(Service.HedgeTrimming);
        dto.setStatus(Status.PENDING);

        // Act
        QuoteRequest entity = mapper.requestModelToEntity(dto);

        // Assert
        assertEquals(dto.getClientId(), entity.getClientId());
        assertEquals(dto.getClientFirstName(), entity.getClientFirstName());
        assertEquals(dto.getClientLastName(), entity.getClientLastName());
        assertEquals(dto.getTime(), entity.getTime());
        assertEquals(dto.getDate(), entity.getDate());
        assertEquals(dto.getPrice(), entity.getPrice());
        assertEquals(dto.getDescription(), entity.getDescription());
        assertEquals(dto.getAddress(), entity.getAddress());
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
