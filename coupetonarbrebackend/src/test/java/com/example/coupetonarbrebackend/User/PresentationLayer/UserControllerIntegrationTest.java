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


}
