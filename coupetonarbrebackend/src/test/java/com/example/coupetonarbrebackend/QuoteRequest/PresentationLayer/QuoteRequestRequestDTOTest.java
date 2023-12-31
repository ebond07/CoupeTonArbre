package com.example.coupetonarbrebackend.QuoteRequest.PresentationLayer;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class QuoteRequestRequestDTOTest {

    @Test
    void generateUUIDString_shouldReturnValidUUID() {
        String generatedUUID = QuoteRequestRequestDTO.generateUUIDString();
        // Assert
        assertNotNull(generatedUUID);
        assertTrue(isValidUUID(generatedUUID));
    }

    private boolean isValidUUID(String uuidString) {
        try {
            UUID uuid = UUID.fromString(uuidString);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}