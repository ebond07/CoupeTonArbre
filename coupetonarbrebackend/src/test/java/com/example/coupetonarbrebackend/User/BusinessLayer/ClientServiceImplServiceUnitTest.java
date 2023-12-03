package com.example.coupetonarbrebackend.User.BusinessLayer;

import com.example.coupetonarbrebackend.User.DataLayer.ClientRepository;

import com.example.coupetonarbrebackend.User.DataMapperLayer.ClientResponseMapper;
import com.example.coupetonarbrebackend.User.DataLayer.Client;
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
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
class ClientServiceImplServiceUnitTest {

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private ClientResponseMapper clientResponseMapper;


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
}
