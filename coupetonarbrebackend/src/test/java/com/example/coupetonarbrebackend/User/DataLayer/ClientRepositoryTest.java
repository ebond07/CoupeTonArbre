/*package com.example.coupetonarbrebackend.User.DataLayer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ClientRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ClientRepository clientRepository;

    @Test
    void shouldFindClientsByFirstName() {
        // Given
        Client client1 = Client.builder().firstName("John").build();
        Client client2 = Client.builder().firstName("Jane").build();
        entityManager.persist(client1);
        entityManager.persist(client2);
        entityManager.flush();

        // When
        List<Client> foundClients = clientRepositor("John");

        // Then
        assertThat(foundClients).hasSize(1);
        assertThat(foundClients.get(0).getFirstName()).isEqualTo("John");
    }

    // Add more tests for other custom queries or business logic
}

 */
