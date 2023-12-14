package com.example.coupetonarbrebackend.User.BusinessLayer;

import com.example.coupetonarbrebackend.User.PresentationLayer.ClientResponseDTO;


import java.util.List;

public interface ClientService {

    List<ClientResponseDTO> getAllClients();
    ClientResponseDTO getClientById(String id);

    void deleteClientByClientId(String clientId);
}
