package com.example.coupetonarbrebackend.QuoteRequest.DataMapperLayer;

import com.example.coupetonarbrebackend.QuoteRequest.DataLayer.QuoteRequest;
import com.example.coupetonarbrebackend.QuoteRequest.PresentationLayer.QuoteRequestDatesResponseDTO;
import com.example.coupetonarbrebackend.QuoteRequest.PresentationLayer.QuoteRequestResponseDTO;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class QuoteRequestDatesResponseMapperUnitTest {

    private final QuoteRequestDatesResponseMapper mapper = Mappers.getMapper(QuoteRequestDatesResponseMapper.class);


    @Test
    void testEntityToResponseModelMapping() {
        QuoteRequest quoteRequest = new QuoteRequest();

        QuoteRequestDatesResponseDTO result = mapper.entityToResponseModel(quoteRequest);


        assertEquals(quoteRequest.getTime(), result.getTime());
        assertEquals(quoteRequest.getDate(), result.getDate());

    }

    @Test
    void shouldMapEntityListToResponseModelList() {
        // Given
        QuoteRequest quoteRequest1 = createQuoteRequest("1", "John", "Doe", "10:00", new Date(2023, 12, 30), 100, "Description");
        QuoteRequest quoteRequest2 = createQuoteRequest("2", "Jane", "Doe", "12:00", new Date(2023, 12, 30), 150, "Another Description");

        // When
        List<QuoteRequestDatesResponseDTO> responseDTOList = mapper.entityListToResponseModelList(Arrays.asList(quoteRequest1, quoteRequest2));

        // Then
        assertEquals(2, responseDTOList.size());

        QuoteRequestDatesResponseDTO responseDTO1 = responseDTOList.get(0);
        QuoteRequestDatesResponseDTO responseDTO2 = responseDTOList.get(1);

        assert(responseDTO1.getTime().equals(quoteRequest1.getTime()));
        assert(responseDTO2.getTime().equals(quoteRequest2.getTime()));
        assert(responseDTO1.getDate().equals(quoteRequest1.getDate()));
        assert(responseDTO2.getDate().equals(quoteRequest2.getDate()));
    }

    private QuoteRequest createQuoteRequest(String quoteRequestId, String clientFirstName, String clientLastName,
                                            String time, Date date, double price, String description) {
        QuoteRequest quoteRequest = new QuoteRequest();
        quoteRequest.setQuoteRequestId(quoteRequestId);
        quoteRequest.setClientFirstName(clientFirstName);
        quoteRequest.setClientLastName(clientLastName);
        quoteRequest.setTime(time);
        quoteRequest.setDate(date);
        quoteRequest.setPrice(price);
        quoteRequest.setDescription(description);
        return quoteRequest;
    }

}