package com.example.coupetonarbrebackend.User.PresentationLayer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientResponseDTOTest {

    @Test
    void testEqualsAndHashCode() {
        ClientResponseDTO client1 = new ClientResponseDTO("c3540a89-cb47-4c96-888e-ff96708db4d8", "Alick", "Ucceli", "aucceli0@dot.gov", "514-837-9347", "73 Shoshone Road, Barraute, Québec, Canada");
        ClientResponseDTO client2 = new ClientResponseDTO("c3540a89-cb47-4c96-888e-ff96708db4d8", "Alick", "Ucceli", "aucceli0@dot.gov", "514-837-9347", "73 Shoshone Road, Barraute, Québec, Canada");

        assertTrue(client1.equals(client2) && client2.equals(client1), "Equals should be symmetric");
        assertEquals(client1.hashCode(), client2.hashCode(), "Hash codes should be equal for equal objects");

        assertFalse(client1.equals(null), "Object should not be equal to null");
        assertFalse(client1.equals("SomeString"), "Object should not be equal to objects of different types");
    }

    @Test
    void testNonEqualObjects() {
        ClientResponseDTO client1 = new ClientResponseDTO("c3540a89-cb47-4c96-888e-ff96708db4d8", "Alick", "Ucceli", "aucceli0@dot.gov", "514-837-9347", "73 Shoshone Road, Barraute, Québec, Canada");
        ClientResponseDTO client2 = new ClientResponseDTO("another-id", "John", "Doe", "john.doe@example.com", "123-456-7890", "123 Main St");

        assertNotEquals(client1, client2, "Objects with different ids should not be equal");
        assertNotEquals(client1.hashCode(), client2.hashCode(), "Hash codes should be different for non-equal objects");
    }

    @Test
    void testNoArgsConstructor() {
        ClientResponseDTO clientResponseDTO = new ClientResponseDTO();
        assertNull(clientResponseDTO.getClientId());
        assertNull(clientResponseDTO.getFirstName());
        assertNull(clientResponseDTO.getLastName());
        assertNull(clientResponseDTO.getEmail());
        assertNull(clientResponseDTO.getPhoneNumber());
        assertNull(clientResponseDTO.getAddress());
    }

    @Test
    void testAllArgsConstructor() {
        String clientId = "c3540a89-cb47-4c96-888e-ff96708db4d8";
        String firstName = "Alick";
        String lastName = "Ucceli";
        String email = "aucceli0@dot.gov";
        String phoneNumber = "514-837-9347";
        String address = "73 Shoshone Road, Barraute, Québec, Canada";

        ClientResponseDTO clientResponseDTO = new ClientResponseDTO(clientId, firstName, lastName, email, phoneNumber, address);

        assertEquals(clientId, clientResponseDTO.getClientId());
        assertEquals(firstName, clientResponseDTO.getFirstName());
        assertEquals(lastName, clientResponseDTO.getLastName());
        assertEquals(email, clientResponseDTO.getEmail());
        assertEquals(phoneNumber, clientResponseDTO.getPhoneNumber());
        assertEquals(address, clientResponseDTO.getAddress());
    }

    @Test
    void testToString() {
        String clientId = "c3540a89-cb47-4c96-888e-ff96708db4d8";
        String firstName = "Alick";
        String lastName = "Ucceli";
        String email = "aucceli0@dot.gov";
        String phoneNumber = "514-837-9347";
        String address = "73ShoshoneRoad,Barraute,Québec,Canada";

        ClientResponseDTO client = new ClientResponseDTO(clientId, firstName, lastName, email, phoneNumber, address);

        String expectedString = "ClientResponseDTO(" +
                "clientId=" + clientId +
                ",firstName=" + firstName +
                ",lastName=" + lastName +
                ",email=" + email +
                ",phoneNumber=" + phoneNumber +
                ",address=" + address +
                ')';

        assertEquals(expectedString, client.toString().replaceAll("\\s", ""), "toString should return the expected string");
    }

    @Test
    void testToStringForBuilder() {
        String clientId = "c3540a89-cb47-4c96-888e-ff96708db4d8";
        String firstName = "Alick";
        String lastName = "Ucceli";
        String email = "aucceli0@dot.gov";
        String phoneNumber = "514-837-9347";
        String address = "73ShoshoneRoad,Barraute,Québec,Canada";

        ClientResponseDTO.ClientResponseDTOBuilder builder = new ClientResponseDTO.ClientResponseDTOBuilder();
        builder.clientId(clientId)
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .phoneNumber(phoneNumber)
                .address(address);

        String expectedString = "ClientResponseDTO.ClientResponseDTOBuilder(" +
                "clientId=" + clientId +
                ",firstName=" + firstName +
                ",lastName=" + lastName +
                ",email=" + email +
                ",phoneNumber=" + phoneNumber +
                ",address=" + address +
                ')';

        assertEquals(expectedString, builder.toString().replaceAll("\\s", ""), "toString should return the expected string");
    }

    @Test
    void testCanEqual() {
        ClientResponseDTO client1 = new ClientResponseDTO("c3540a89-cb47-4c96-888e-ff96708db4d8", "Alick", "Ucceli", "aucceli0@dot.gov", "514-837-9347", "73 Shoshone Road, Barraute, Québec, Canada");
        ClientResponseDTO client2 = new ClientResponseDTO("c3540a89-cb47-4c96-888e-ff96708db4d8", "Alick", "Ucceli", "aucceli0@dot.gov", "514-837-9347", "73 Shoshone Road, Barraute, Québec, Canada");

        assertTrue(client1.canEqual(client2), "canEqual should return true for equal objects");
    }
    @Test
    void testEqualsWithDifferentClass() {
        ClientResponseDTO clientResponseDTO = new ClientResponseDTO("c3540a89-cb47-4c96-888e-ff96708db4d8", "Alick", "Ucceli", "aucceli0@dot.gov", "514-837-9347", "73 Shoshone Road, Barraute, Québec, Canada");

        assertFalse(clientResponseDTO.equals("SomeString"), "Object should not be equal to objects of different types");
    }

    @Test
    void testEqualsWithNull() {
        ClientResponseDTO clientResponseDTO = new ClientResponseDTO("c3540a89-cb47-4c96-888e-ff96708db4d8", "Alick", "Ucceli", "aucceli0@dot.gov", "514-837-9347", "73 Shoshone Road, Barraute, Québec, Canada");

        assertFalse(clientResponseDTO.equals(null), "Object should not be equal to null");
    }


}
