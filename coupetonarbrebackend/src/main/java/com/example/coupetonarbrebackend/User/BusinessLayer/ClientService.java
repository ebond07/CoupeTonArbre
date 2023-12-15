package com.example.coupetonarbrebackend.User.BusinessLayer;

import com.example.coupetonarbrebackend.User.PresentationLayer.ClientResponseDTO;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

public interface ClientService {

    List<ClientResponseDTO> getAllClients();


    void deleteClientByClientId(String clientId);
}
