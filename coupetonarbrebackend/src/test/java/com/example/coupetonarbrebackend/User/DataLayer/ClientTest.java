package com.example.coupetonarbrebackend.User.DataLayer;

import com.example.coupetonarbrebackend.User.DataLayer.Client;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ClientTest {

    @Test
    void testGettersAndSetters() {

        Client client = new Client();

        // When
        client.setId(1);
        client.setClientId("c3540a89-cb47-4c96-888e-ff96708db4d8");
        client.setFirstName("Alick");
        client.setLastName("Ucceli");
        client.setEmail("aucceli0@dot.gov");
        client.setPhoneNumber("514-837-9347");
        client.setAddress("73 Shoshone Road, Barraute, Québec, Canada");

        // Then
        assertEquals(1, client.getId());
        assertEquals("c3540a89-cb47-4c96-888e-ff96708db4d8", client.getClientId());
        assertEquals("Alick", client.getFirstName());
        assertEquals("Ucceli", client.getLastName());
        assertEquals("aucceli0@dot.gov", client.getEmail());
        assertEquals("514-837-9347", client.getPhoneNumber());
        assertEquals("73 Shoshone Road, Barraute, Québec, Canada", client.getAddress());
    }

    @Test
    void testNoArgsConstructor() {
        Client client = new Client();
        assertNull(client.getId());
        assertNull(client.getClientId());
        assertNull(client.getFirstName());
        assertNull(client.getLastName());
        assertNull(client.getEmail());
        assertNull(client.getPhoneNumber());
        assertNull(client.getAddress());
    }

    @Test
    void testAllArgsConstructor() {

        Client client = new Client(1, "c3540a89-cb47-4c96-888e-ff96708db4d8", "Alick", "Ucceli", "aucceli0@dot.gov", "514-837-9347", "73 Shoshone Road, Barraute, Québec, Canada");


        assertEquals(1, client.getId());
        assertEquals("c3540a89-cb47-4c96-888e-ff96708db4d8", client.getClientId());
        assertEquals("Alick", client.getFirstName());
        assertEquals("Ucceli", client.getLastName());
        assertEquals("aucceli0@dot.gov", client.getEmail());
        assertEquals("514-837-9347", client.getPhoneNumber());
        assertEquals("73 Shoshone Road, Barraute, Québec, Canada", client.getAddress());
    }


    @Test
    void testBuilder() {
        // Given
        String clientId = "c3540a89-cb47-4c96-888e-ff96708db4d8";
        String firstName = "Alick";
        String lastName = "Ucceli";
        String email = "aucceli0@dot.gov";
        String phoneNumber = "514-837-9347";
        String address = "73 Shoshone Road, Barraute, Québec, Canada";

        // When
        Client client = Client.builder()
                .id(1)
                .clientId(clientId)
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .phoneNumber(phoneNumber)
                .address(address)
                .build();

        // Then
        assertEquals(1, client.getId());
        assertEquals(clientId, client.getClientId());
        assertEquals(firstName, client.getFirstName());
        assertEquals(lastName, client.getLastName());
        assertEquals(email, client.getEmail());
        assertEquals(phoneNumber, client.getPhoneNumber());
        assertEquals(address, client.getAddress());
    }

    @Test
    void testBuilderToString() {

        Client.ClientBuilder builder = Client.builder()
                .id(1)
                .clientId("c3540a89-cb47-4c96-888e-ff96708db4d8")
                .firstName("Alick")
                .lastName("Ucceli")
                .email("aucceli0@dot.gov")
                .phoneNumber("514-837-9347")
                .address("73 Shoshone Road, Barraute, Québec, Canada");


        String toStringResult = builder.toString();


        String expectedToString = "Client.ClientBuilder(id=1, clientId=c3540a89-cb47-4c96-888e-ff96708db4d8, firstName=Alick, lastName=Ucceli, email=aucceli0@dot.gov, phoneNumber=514-837-9347, address=73 Shoshone Road, Barraute, Québec, Canada)";
        assertEquals(expectedToString, toStringResult, "ClientBuilder.toString() result should match the expected string");
    }
    @Test
    void testEqualsAndHashCode() {
        Client client1 = new Client(1, "c3540a89-cb47-4c96-888e-ff96708db4d8", "Alick", "Ucceli", "aucceli0@dot.gov", "514-837-9347", "73 Shoshone Road, Barraute, Québec, Canada");
        Client client2 = new Client(1, "c3540a89-cb47-4c96-888e-ff96708db4d8", "Alick", "Ucceli", "aucceli0@dot.gov", "514-837-9347", "73 Shoshone Road, Barraute, Québec, Canada");

        assertTrue(client1.equals(client2) && client2.equals(client1), "Equals should be symmetric");
        assertEquals(client1.hashCode(), client2.hashCode(), "Hash codes should be equal for equal objects");

        assertFalse(client1.equals(null), "Object should not be equal to null");
        assertFalse(client1.equals("SomeString"), "Object should not be equal to objects of different types");
    }


    @Test
    void testNonEqualObjects() {

        Client client1 = new Client(1, "c3540a89-cb47-4c96-888e-ff96708db4d8", "Alick", "Ucceli", "aucceli0@dot.gov", "514-837-9347", "73 Shoshone Road, Barraute, Québec, Canada");
        Client client2 = new Client(2, "another-id", "John", "Doe", "john.doe@example.com", "123-456-7890", "123 Main St");


        assertNotEquals(client1, client2, "Objects with different ids should not be equal");
        assertNotEquals(client1.hashCode(), client2.hashCode(), "Hash codes should be different for non-equal objects");
    }


    @Test
    void testToString() {

        Client client = new Client(1, "c3540a89-cb47-4c96-888e-ff96708db4d8", "Alick", "Ucceli", "aucceli0@dot.gov", "514-837-9347", "73 Shoshone Road, Barraute, Québec, Canada");

        String toStringResult = client.toString();


        String expectedToString = "Client(id=1, clientId=c3540a89-cb47-4c96-888e-ff96708db4d8, firstName=Alick, lastName=Ucceli, email=aucceli0@dot.gov, phoneNumber=514-837-9347, address=73 Shoshone Road, Barraute, Québec, Canada)";
        assertEquals(expectedToString, toStringResult, "toString() result should match the expected string");
    }
    @Test
    void testCanEqual() {
        Client client1 = new Client(1, "c3540a89-cb47-4c96-888e-ff96708db4d8", "Alick", "Ucceli", "aucceli0@dot.gov", "514-837-9347", "73 Shoshone Road, Barraute, Québec, Canada");
        Client client2 = new Client(1, "c3540a89-cb47-4c96-888e-ff96708db4d8", "Alick", "Ucceli", "aucceli0@dot.gov", "514-837-9347", "73 Shoshone Road, Barraute, Québec, Canada");

        assertTrue(client1.canEqual(client2), "canEqual should return true for equal objects");
    }
    @Test
    void testEquals() {
        Client client1 = new Client(1, "clientId", "John", "Doe", "john.doe@example.com", "123-456-7890", "123 Main St");
        Client client2 = new Client(1, "clientId", "John", "Doe", "john.doe@example.com", "123-456-7890", "123 Main St");
        Client client3 = new Client(2, "otherId", "Jane", "Doe", "jane.doe@example.com", "987-654-3210", "456 Secondary St");


        assertThat(client1).isEqualTo(client2);

        assertThat(client1).isNotEqualTo(client3);

        assertThat(client1).isNotEqualTo(null).isNotEqualTo("SomeString");
    }
    @Test
    void testHashCode() {
        Client client1 = new Client(1, "clientId", "John", "Doe", "john.doe@example.com", "123-456-7890", "123 Main St");
        Client client2 = new Client(1, "clientId", "John", "Doe", "john.doe@example.com", "123-456-7890", "123 Main St");
        Client client3 = new Client(2, "otherId", "Jane", "Doe", "jane.doe@example.com", "987-654-3210", "456 Secondary St");


        assertThat(client1.hashCode()).isEqualTo(client2.hashCode());

        assertThat(client1.hashCode()).isNotEqualTo(client3.hashCode());
    }


}