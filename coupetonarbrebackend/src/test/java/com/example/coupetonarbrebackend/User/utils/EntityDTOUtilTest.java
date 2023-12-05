package com.example.coupetonarbrebackend.User.utils;

import com.example.coupetonarbrebackend.User.DataLayer.Client;
import com.example.coupetonarbrebackend.User.PresentationLayer.ClientResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class EntityDTOUtilTest {

    @Test
    void testToClientResponseDto() {
        // Arrange
        Client client = new Client();
        client.setClientId("123");
        client.setFirstName("John");
        client.setLastName("Doe");
        client.setEmail("john.doe@example.com");

        // Act
        ClientResponseDTO clientResponseDTO = EntityDTOUtil.toClientResponseDto(client);

        // Assert
        assertNotNull(clientResponseDTO);
        assertEquals(client.getClientId(), clientResponseDTO.getClientId());
        assertEquals(client.getFirstName(), clientResponseDTO.getFirstName());
        assertEquals(client.getLastName(), clientResponseDTO.getLastName());
        assertEquals(client.getEmail(), clientResponseDTO.getEmail());
    }



    @Test
    void testGenerateUUIDString() {
        // Act
        String uuidString = EntityDTOUtil.generateUUIDString();

        // Assert
        assertNotNull(uuidString);

    }
}
