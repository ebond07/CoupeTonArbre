package com.example.coupetonarbrebackend.User.BusinessLayer;

import com.example.coupetonarbrebackend.User.DataLayer.ClientRepository;

import com.example.coupetonarbrebackend.User.DataMapperLayer.ClientRequestMapper;
import com.example.coupetonarbrebackend.User.DataMapperLayer.ClientResponseMapper;
import com.example.coupetonarbrebackend.User.DataLayer.Client;
import com.example.coupetonarbrebackend.User.PresentationLayer.ClientRequestDTO;
import com.example.coupetonarbrebackend.User.PresentationLayer.ClientResponseDTO;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
class ClientServiceImplServiceUnitTest {

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private ClientResponseMapper clientResponseMapper;

    @Mock
    private ClientRequestMapper clientRequestMapper;


    @InjectMocks
    private ClientServiceImpl clientService;

    @Test
    void getAllClients_shouldReturnClientResponseDTOList() {
        // Arrange
        List<Client> mockClientList = Arrays.asList(
                new Client()

        );

        List<ClientResponseDTO> expectedResponseDTOList = Arrays.asList(
                new ClientResponseDTO("c3540a89-cb47-4c96-888e-ff96708db4d8", "Alick", "Ucceli", "aucceli0@dot.gov", "514-837-9347", "73 Shoshone Road, Barraute, Québec, Canada"),
                new ClientResponseDTO("yetAnotherClientId", "Jane", "Smith", "jane.smith@example.com", "987-654-3210", "456 Secondary St")
        );

        when(clientRepository.findAll()).thenReturn(mockClientList);
        when(clientResponseMapper.entityListToResponseModelList(mockClientList)).thenReturn(expectedResponseDTOList);
        List<ClientResponseDTO> actualResponseDTOList = clientService.getAllClients();
        assertEquals(expectedResponseDTOList.size(), actualResponseDTOList.size());

    }

    @Test
    void updateClient_withValidClientId_shouldReturnUpdatedClient() {
        // Arrange
        String clientId = "C1";

        ClientRequestDTO clientRequestDTO = ClientRequestDTO.builder()
                .clientId(clientId)
                .firstName("David")
                .lastName("Rallo")
                .email("david@gmail.com")
                .phoneNumber("514-875-7267")
                .address("1234 rue de la montagne")
                .build();

        Client existingClient = Client.builder()
                .clientId(clientId)
                .firstName("John")
                .lastName("Doe")
                .email("john.doe@example.com")
                .phoneNumber("555-1234")
                .address("123 Main St")
                .build();

        when(clientRepository.findByClientId(clientId)).thenReturn(existingClient);
        when(clientRequestMapper.requestModelToEntity(clientRequestDTO)).thenReturn(existingClient);
        when(clientRepository.save(any(Client.class))).thenReturn(existingClient);
        when(clientResponseMapper.entityToResponseModel(existingClient)).thenReturn(ClientResponseDTO.builder()
                .clientId(clientId)
                .firstName("David")
                .lastName("Rallo")
                .email("david@gmail.com")
                .phoneNumber("514-875-7267")
                .address("1234 rue de la montagne")
                .build());

        // Act
        ClientResponseDTO updatedClient = clientService.updateClient(clientRequestDTO, clientId);

        // Assert
        assertNotNull(updatedClient);
        assertEquals(clientId, updatedClient.getClientId());
        assertEquals("David", updatedClient.getFirstName());
        assertEquals("Rallo", updatedClient.getLastName());
        assertEquals("david@gmail.com", updatedClient.getEmail());
        assertEquals("514-875-7267", updatedClient.getPhoneNumber());
        assertEquals("1234 rue de la montagne", updatedClient.getAddress());
    }

    @Test
    void updateClient_withNonexistentClientId_shouldReturnNull() {
        // Arrange
        String clientId = "NonexistentId";
        ClientRequestDTO clientRequestDTO = ClientRequestDTO.builder()
                .clientId(clientId)
                .firstName("David")
                .lastName("Rallo")
                .email("david@gmail.com")
                .phoneNumber("514-875-7267")
                .address("1234 rue de la montagne")
                .build();

        when(clientRepository.findByClientId(clientId)).thenReturn(null);

        // Act
        ClientResponseDTO updatedClient = clientService.updateClient(clientRequestDTO, clientId);

        // Assert
        assertNull(updatedClient);
    }




}
