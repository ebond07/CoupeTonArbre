package com.example.coupetonarbrebackend.User.DataMapperLayer;

import com.example.coupetonarbrebackend.User.DataLayer.Client;

import com.example.coupetonarbrebackend.User.PresentationLayer.ClientRequestDTO;
import com.example.coupetonarbrebackend.User.PresentationLayer.ClientResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ClientMapperUnitTest {
    private ClientResponseMapper responseMapper;
    private ClientRequestMapper requestMapper;


    @BeforeEach
    void setUp() {

        responseMapper = Mappers.getMapper(ClientResponseMapper.class);
        requestMapper = Mappers.getMapper(ClientRequestMapper.class);

    }


    @Test
    void testEntityToResponseModelMapping() {

        Client client = new Client();


        ClientResponseDTO result = responseMapper.entityToResponseModel(client);

        assertEquals(client.getClientId(), result.getClientId());
        assertEquals(client.getFirstName(), result.getFirstName());

    }

    @Test
    void testRequestDTOToEntityMapping() {

        ClientRequestDTO clientRequestDTO = new ClientRequestDTO();


        Client result = requestMapper.requestModelToEntity(clientRequestDTO);

        assertEquals(clientRequestDTO.getFirstName(), result.getFirstName());

    }

    @Test
    void testEntityListToResponseModelListMapping() {

        List<Client> clients = Arrays.asList(new Client(), new Client());
        List<ClientResponseDTO> result = responseMapper.entityListToResponseModelList(clients);
        assertEquals(clients.size(), result.size());

    }
}
