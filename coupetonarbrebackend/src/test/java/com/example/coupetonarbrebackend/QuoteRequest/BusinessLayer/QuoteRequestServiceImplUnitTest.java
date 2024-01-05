package com.example.coupetonarbrebackend.QuoteRequest.BusinessLayer;

import com.example.coupetonarbrebackend.QuoteRequest.DataLayer.QuoteRequest;
import com.example.coupetonarbrebackend.QuoteRequest.DataLayer.QuoteRequestRepository;

import com.example.coupetonarbrebackend.QuoteRequest.DataMapperLayer.QuoteRequestDatesResponseMapper;
import com.example.coupetonarbrebackend.QuoteRequest.DataMapperLayer.QuoteRequestRequestMapper;
import com.example.coupetonarbrebackend.QuoteRequest.DataMapperLayer.QuoteRequestResponseMapper;
import com.example.coupetonarbrebackend.QuoteRequest.PresentationLayer.QuoteRequestDatesResponseDTO;
import com.example.coupetonarbrebackend.QuoteRequest.PresentationLayer.QuoteRequestRequestDTO;
import com.example.coupetonarbrebackend.QuoteRequest.PresentationLayer.QuoteRequestResponseDTO;
import com.example.coupetonarbrebackend.QuoteRequest.DataLayer.Service;

import com.example.coupetonarbrebackend.QuoteRequest.DataLayer.Status;
import com.example.coupetonarbrebackend.User.BusinessLayer.ClientService;
import com.example.coupetonarbrebackend.User.PresentationLayer.ClientResponseDTO;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class QuoteRequestServiceImplUnitTest {

    @Mock
    private QuoteRequestRepository quoteRequestRepository;

    @Mock
    private QuoteRequestResponseMapper quoteRequestResponseMapper;

    @Mock
    private QuoteRequestDatesResponseMapper quoteRequestDatesResponseMapper;

    @Mock
    private QuoteRequestRequestMapper quoteRequestRequestMapper;

    @InjectMocks
    private QuoteRequestServiceImpl quoteRequestService;



    @Mock
    private ClientService clientService;



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

        @Test
    void getAllQuoteRequestsDates_shouldReturnQuoteRequestDatesResponseDTOList() {
        // Arrange
        List<QuoteRequest> mockQuoteRequestList = Arrays.asList(
                new QuoteRequest(1, "1", "1", "firstName", "lastName", "9:00", new Date(), 100.00, "description", com.example.coupetonarbrebackend.QuoteRequest.DataLayer.Service.HedgeTrimming, com.example.coupetonarbrebackend.QuoteRequest.DataLayer.Status.PENDING),
                new QuoteRequest(2, "2", "2", "firstName", "lastName", "10:00", new Date(), 200.00, "description", com.example.coupetonarbrebackend.QuoteRequest.DataLayer.Service.SmallTreeRemoval, com.example.coupetonarbrebackend.QuoteRequest.DataLayer.Status.PENDING)
        );

        List<QuoteRequestDatesResponseDTO> expectedResponseDTOList = Arrays.asList(
                new QuoteRequestDatesResponseDTO("9:00", new Date()),
                new QuoteRequestDatesResponseDTO("10:00", new Date())
        );
        // Act
        when(quoteRequestRepository.findAll()).thenReturn(mockQuoteRequestList);
        when(quoteRequestDatesResponseMapper.entityListToResponseModelList(mockQuoteRequestList)).thenReturn(expectedResponseDTOList);
        List<QuoteRequestDatesResponseDTO> resultResponseDTOList = quoteRequestService.getAllQuoteRequestsDates();
        // Assert
        assertEquals(expectedResponseDTOList, resultResponseDTOList);
    }



    @Test
    void testAdminCreateQuoteRequest() {
        // Mock data
        QuoteRequestRequestDTO requestDTO =  QuoteRequestRequestDTO.builder()
                .clientId("client123")
                .clientFirstName("firstName")
                .clientLastName("lastName")
                .time("9:00")
                .date(new Date())
                .price(100.00)
                .description("description")
                .build();


        QuoteRequest quoteRequest = QuoteRequest.builder()
                .quoteRequestId("123")
                .clientId("client123")
                .clientFirstName("firstName")
                .clientLastName("lastName")
                .time("9:00")
                .date(new Date())
                .price(100.00)
                .description("description")
                .service(Service.HedgeTrimming)
                .status(Status.QUOTE_SENT)
                .build();

        // Mocking
        when(quoteRequestRequestMapper.requestModelToEntity(requestDTO)).thenReturn(quoteRequest);
        when(quoteRequestRepository.save(any(QuoteRequest.class))).thenReturn(quoteRequest);

        when(quoteRequestResponseMapper.entityToResponseModel(quoteRequest)).thenReturn(new QuoteRequestResponseDTO());

        QuoteRequestResponseDTO responseDTO = quoteRequestService.adminCreateQuoteRequest(requestDTO);

        // Assertions
        assertNotNull(responseDTO);

        verify(quoteRequestRequestMapper).requestModelToEntity(requestDTO);
        verify(quoteRequestRepository).save(quoteRequest);
        verify(quoteRequestResponseMapper).entityToResponseModel(quoteRequest);
    }

    @Test
    void testClientCreateQuoteRequest() {
        // Mock data
        QuoteRequestRequestDTO requestDTO = new QuoteRequestRequestDTO();
        String clientId = "client123";
        QuoteRequest quoteRequest = new QuoteRequest();
        quoteRequest.setQuoteRequestId("123");
        quoteRequest.setStatus(Status.QUOTE_SENT);

        ClientResponseDTO clientResponseDTO = new ClientResponseDTO();
        clientResponseDTO.setClientId(clientId);

        // Mocking
        when(quoteRequestRequestMapper.requestModelToEntity(requestDTO)).thenReturn(quoteRequest);
        when(clientService.getClientById(clientId)).thenReturn(clientResponseDTO);
        when(quoteRequestRepository.save(quoteRequest)).thenReturn(quoteRequest);
        when(quoteRequestResponseMapper.entityToResponseModel(quoteRequest)).thenReturn(new QuoteRequestResponseDTO());

        // Actual method call
        QuoteRequestResponseDTO responseDTO = quoteRequestService.clientCreateQuoteRequest(requestDTO, clientId);

        // Assertions
        assertNotNull(responseDTO);

        verify(quoteRequestRequestMapper).requestModelToEntity(requestDTO);
        verify(clientService).getClientById(clientId);
        verify(quoteRequestRepository).save(quoteRequest);
        verify(quoteRequestResponseMapper).entityToResponseModel(quoteRequest);
    }
}