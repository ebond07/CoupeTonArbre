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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.ExpectedCount.times;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
    void whenValidClientId_thenUpdateClient() {
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

        Client existingClient = Client.builder().build();

        when(clientRepository.findByClientId(clientId)).thenReturn(existingClient);
        when(clientRequestMapper.requestModelToEntity(clientRequestDTO)).thenReturn(existingClient);

        Client updatedClient = Client.builder()
                .clientId(clientId)
                .firstName("David")
                .lastName("Rallo")
                .email("david@gmail.com")
                .phoneNumber("514-875-7267")
                .address("1234 rue de la montagne")
                .build();

        when(clientRepository.save(any(Client.class))).thenReturn(updatedClient);

        // Act
        ClientResponseDTO clientResponseDTO = clientService.updateClient(clientRequestDTO, clientId);

        // Assert
        assertEquals(clientId, clientResponseDTO.getClientId());

        // Verify that the repository's findByClientId method and save method were called
        verify(clientRepository, times(1)).findByClientId(clientId);
        verify(clientRepository, times(1)).save(any(Client.class));

        // Verify that the request mapper's method was called
        verify(clientRequestMapper, times(1)).requestModelToEntity(clientRequestDTO);
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
    void deleteClientByClientId_shouldDeleteClient() {
        // Arrange
        String clientIdToDelete = "c3540a89-cb47-4c96-888e-ff96708db4d8";

        // Act
        clientService.deleteClientByClientId(clientIdToDelete);

        // Assert
        verify(clientRepository).deleteByClientId(clientIdToDelete);
    }

}
