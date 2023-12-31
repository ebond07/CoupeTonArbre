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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

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


    @Test
    void checkIfClientExists() {

        String clientId = "google|123456789";

        when(clientRepository.existsByClientId(clientId)).thenReturn(true);

        boolean result = clientService.checkIfClientExists(clientId);

        assertTrue(result);
    }

    @Test
    void getClientById_shouldReturnClientResponseDTO() {
        // Arrange
        String clientId = "c3540a89-cb47-4c96-888e-ff96708db4d8";
        Client mockClient = new Client();
        ClientResponseDTO expectedResponseDTO = new ClientResponseDTO(clientId, "Alick", "Ucceli", "aucceli0@dot.gov", "514-837-9347", "73 Shoshone Road, Barraute, Québec, Canada");

        when(clientRepository.findClientByClientId(clientId)).thenReturn(mockClient);
        when(clientResponseMapper.entityToResponseModel(mockClient)).thenReturn(expectedResponseDTO);

        // Act
        ClientResponseDTO actualResponseDTO = clientService.getClientById(clientId);

        // Assert
        assertEquals(expectedResponseDTO, actualResponseDTO);
    }

    @Test
    void addClient_shouldSucceed() {
        // Arrange
        Client newClient = new Client();
        newClient.setFirstName("John");
        newClient.setLastName("Doe");
        newClient.setEmail("john.doe@example.com");
        newClient.setPhoneNumber("123-456-7890");
        newClient.setAddress("123 Main St");

        String generatedClientId = "generatedClientId";
        when(clientRepository.save(any(Client.class))).thenAnswer(invocation -> {
            Client savedClient = invocation.getArgument(0);
            savedClient.setClientId(generatedClientId);
            return savedClient;
        });

        ClientResponseDTO expectedResponseDTO = new ClientResponseDTO(generatedClientId, "John", "Doe", "john.doe@example.com", "123-456-7890", "123 Main St");
        when(clientResponseMapper.entityToResponseModel(any(Client.class))).thenReturn(expectedResponseDTO);

        // Act
        ClientResponseDTO actualResponseDTO = clientService.adminAddClient(newClient);

        // Assert
        verify(clientRepository).save(any(Client.class));
        verify(clientResponseMapper).entityToResponseModel(any(Client.class));
        assertEquals(expectedResponseDTO, actualResponseDTO);
    }



    @Test
    void clientCreateProfile() {

        String clientId = "google|123456789";

        ClientRequestDTO mockClientRequest = ClientRequestDTO.builder()
                .firstName("Alice")
                .lastName("Doe")
                .email("test@email.com")
                .phoneNumber("514-234-4323")
                .address("123 Main St")
                .build();

        Client mockClient = Client.builder()
                .firstName("Alice")
                .lastName("Doe")
                .email("test@email.com")
                .phoneNumber("514-234-4323")
                .address("123 Main St")
                .build();

        ClientResponseDTO mockClientResponse = ClientResponseDTO.builder()
                .firstName("Alice")
                .lastName("Doe")
                .email("test@email.com")
                .phoneNumber("514-234-4323")
                .address("123 Main St")
                .build();

        when(clientRepository.existsByClientId(clientId)).thenReturn(false);

        when(clientRequestMapper.requestModelToEntity(mockClientRequest)).thenReturn(mockClient);

        when(clientRepository.save(mockClient)).thenReturn(mockClient);

        when(clientResponseMapper.entityToResponseModel(mockClient)).thenReturn(mockClientResponse);

        ClientResponseDTO result = clientService.clientCreateProfile(mockClientRequest, clientId);

        assertEquals(mockClient.getFirstName(), result.getFirstName());

        assertEquals(mockClient.getLastName(), result.getLastName());

        assertEquals(mockClient.getEmail(), result.getEmail());

        assertEquals(mockClient.getPhoneNumber(), result.getPhoneNumber());

        assertEquals(mockClient.getAddress(), result.getAddress());




    }

    @Test
    void updateProfile() {

        String clientId = "google|123456789";

        ClientRequestDTO mockClientRequest = ClientRequestDTO.builder()
                .firstName("Alice")
                .lastName("Doe")
                .email("test@email.com")
                .phoneNumber("new number")
                .address("new address")
                .build();

        Client mockClient = Client.builder()
                .firstName("Alice")
                .lastName("Doe")
                .email("test@email.com")
                .phoneNumber("514-234-4323")
                .address("123 Main St")
                .build();

        ClientResponseDTO mockClientResponse = ClientResponseDTO.builder()
                .firstName("Alice")
                .lastName("Doe")
                .email("test@email.com")
                .phoneNumber("new number")
                .address("new address")
                .build();



        when(clientRepository.findClientByClientId(clientId)).thenReturn(mockClient);


        when(clientRequestMapper.requestModelToEntity(mockClientRequest)).thenReturn(mockClient);

        when(clientRepository.save(mockClient)).thenReturn(mockClient);

        when(clientResponseMapper.entityToResponseModel(mockClient)).thenReturn(mockClientResponse);


        ClientResponseDTO result = clientService.updateProfile(mockClientRequest, clientId);

        assertEquals(mockClient.getFirstName(), result.getFirstName());

        assertEquals(mockClient.getLastName(), result.getLastName());

        assertEquals(mockClient.getEmail(), result.getEmail());

        assertEquals(mockClient.getPhoneNumber(), result.getPhoneNumber());

        assertEquals(mockClient.getAddress(), result.getAddress());



    }

    @Test
    void deleteClientByClientId_shouldDeleteClient() {
        // Arrange
        String clientIdToDelete = "c3540a89-cb47-4c96-888e-ff96708db4d8";

        // Act
        clientService.deleteClientByClientId(clientIdToDelete);

        // Assert
        verify(clientRepository).deleteByClientId(clientIdToDelete);
    }

}
