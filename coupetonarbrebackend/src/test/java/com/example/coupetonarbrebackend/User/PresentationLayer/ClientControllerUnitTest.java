package com.example.coupetonarbrebackend.User.PresentationLayer;

import com.example.coupetonarbrebackend.User.BusinessLayer.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class ClientControllerUnitTest {

    @Mock
    private ClientService clientService;

    @InjectMocks
    private ClientController clientController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllClients_shouldReturnListOfClientResponseDTO() {
        List<ClientResponseDTO> expectedClientResponseDTOList = Arrays.asList(
                new ClientResponseDTO("c3540a89-cb47-4c96-888e-ff96708db4d8", "Alick", "Ucceli", "aucceli0@dot.gov", "514-837-9347", "73 Shoshone Road, Barraute, Québec, Canada"),
                new ClientResponseDTO("dd1ab8b0-ab17-4e03-b70a-84caa3871606", "Ricky", "Presslie", "rpresslie1@domainmarket.com", "514-237-9384", "24 Dorton Circle, Notre-Dame-des-Prairies, Québec, Canada")
        );

        when(clientService.getAllClients()).thenReturn(expectedClientResponseDTOList);

        ResponseEntity<List<ClientResponseDTO>> responseEntity = clientController.getAllClients();


        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedClientResponseDTOList, responseEntity.getBody());
    }
}
