package com.example.coupetonarbrebackend.User.PresentationLayer;

import com.example.coupetonarbrebackend.User.BusinessLayer.ClientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class UserControllerIntegrationTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private ClientService clientService;

    @Test
    void getAllClients_shouldSucceed() {

        when(clientService.getAllClients()).thenReturn(Arrays.asList(
                ClientResponseDTO.builder().clientId("1").firstName("John").build(),
                ClientResponseDTO.builder().clientId("2").firstName("Jane").build()
        ));


        webTestClient.get()
                .uri("/users/clients")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBodyList(ClientResponseDTO.class)
                .value(clientResponseDTOS -> {
                    assert clientResponseDTOS != null;
                    assert clientResponseDTOS.size() == 2;
                    assert clientResponseDTOS.get(0).getClientId().equals("1");
                    assert clientResponseDTOS.get(1).getClientId().equals("2");
                });
    }
    @Test
    void getClientById_shouldSucceed() {
        // Arrange
        String clientId = "1";
        when(clientService.getClientById(clientId))
                .thenReturn(ClientResponseDTO.builder().clientId(clientId).firstName("John").build());

        webTestClient.get()
                .uri("/users/clients/{clientId}", clientId)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(ClientResponseDTO.class)
                .value(clientResponseDTO -> {
                    assert clientResponseDTO != null;
                    assert clientResponseDTO.getClientId().equals(clientId);
                    // Add more assertions based on your expected response
                });
    }

    @Test
    void updateClient_shouldSucceed() {
        // Mock data
        String clientId = "1";
        ClientRequestDTO requestDTO = ClientRequestDTO.builder()
                .clientId(clientId)
                .firstName("John")
                .build();

        ClientResponseDTO responseDTO = ClientResponseDTO.builder()
                .clientId(clientId)
                .firstName("John")
                .build();

        // Mock service method
        when(clientService.updateClient(requestDTO, clientId)).thenReturn(responseDTO);

        // Perform the request and validate the response
        webTestClient.put()
                .uri("/users/clients/{clientId}", clientId)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(requestDTO)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(ClientResponseDTO.class)
                .value(clientResponseDTO -> {
                    assert clientResponseDTO != null;
                    assert clientResponseDTO.getClientId().equals(clientId);
                    assert clientResponseDTO.getFirstName().equals("John");
                });
    }


    @Test
    void deleteClientById_shouldSucceed() {
        // Arrange
        String clientIdToDelete = "1";

        // Perform the actual HTTP request and assert the response
        webTestClient.delete()
                .uri("/users/clients/{clientId}", clientIdToDelete)
                .exchange()
                .expectStatus().isNoContent();
    }
}
