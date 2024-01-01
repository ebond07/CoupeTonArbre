package com.example.coupetonarbrebackend.QuoteRequest.BusinessLayer;

import com.example.coupetonarbrebackend.QuoteRequest.DataLayer.QuoteRequest;
import com.example.coupetonarbrebackend.QuoteRequest.DataLayer.QuoteRequestRepository;

import com.example.coupetonarbrebackend.QuoteRequest.DataMapperLayer.QuoteRequestRequestMapper;
import com.example.coupetonarbrebackend.QuoteRequest.DataMapperLayer.QuoteRequestResponseMapper;
import com.example.coupetonarbrebackend.QuoteRequest.PresentationLayer.QuoteRequestResponseDTO;
import com.example.coupetonarbrebackend.QuoteRequest.PresentationLayer.Service;
import com.example.coupetonarbrebackend.QuoteRequest.PresentationLayer.Status;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class QuoteRequestServiceImplUnitTest {

    @Mock
    private QuoteRequestRepository quoteRequestRepository;

    @Mock
    private QuoteRequestResponseMapper quoteRequestResponseMapper;

    @Mock
    private QuoteRequestRequestMapper quoteRequestRequestMapper;

    @Mock
    private QuoteRequestServiceImpl quoteRequestService;

//    @Test
//    void getAllQuoteRequests_shouldReturnQuoteRequestResponseDTOList() {
//        // Arrange
//        List<QuoteRequest> mockQuoteRequestList = Arrays.asList(
//                new QuoteRequest(1, "1", "1", "firstName", "lastName", "9:00", new Date(), 100.00, "description", com.example.coupetonarbrebackend.QuoteRequest.DataLayer.Service.HedgeTrimming, com.example.coupetonarbrebackend.QuoteRequest.DataLayer.Status.PENDING),
//                new QuoteRequest(2, "2", "2", "firstName", "lastName", "10:00", new Date(), 200.00, "description", com.example.coupetonarbrebackend.QuoteRequest.DataLayer.Service.SmallTreeRemoval, com.example.coupetonarbrebackend.QuoteRequest.DataLayer.Status.PENDING)
//        );
//
//        List<QuoteRequestResponseDTO> expectedResponseDTOList = Arrays.asList(
//                new QuoteRequestResponseDTO("c577e9ca-7044-43e1-87b6-2d8c823e3877", "c3540a89-cb47-4c96-888e-ff96708db4d8", "Alick", "Ucceli", "9:00", new Date(), 100.0, "I need a hedge trimmed", Service.HedgeTrimming, Status.PENDING),
//                new QuoteRequestResponseDTO("5ba7f9ce-881c-45c6-97d0-dd7720e84be1", "dd1ab8b0-ab17-4e03-b70a-84caa3871606", "Jane", "Smith", "10:00", new Date(), 200.0, "I need a tree cut down", Service.SmallTreeRemoval, Status.PENDING)
//        );
//        // Act
//        when(quoteRequestRepository.findAll()).thenReturn(mockQuoteRequestList);
//        when(quoteRequestResponseMapper.entityListToResponseModelList(mockQuoteRequestList)).thenReturn(expectedResponseDTOList);
//        List<QuoteRequestResponseDTO> resultResponseDTOList = quoteRequestService.getAllQuoteRequests();
//        // Assert
//        assertEquals(expectedResponseDTOList, resultResponseDTOList);
//    }
}