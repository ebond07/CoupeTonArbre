package com.example.coupetonarbrebackend.User.BusinessLayer;

import com.example.coupetonarbrebackend.User.PresentationLayer.ClientRequestDTO;
import com.example.coupetonarbrebackend.User.PresentationLayer.ClientResponseDTO;


import java.util.List;

public interface ClientService {

    List<ClientResponseDTO> getAllClients();

    ClientResponseDTO updateClient(ClientRequestDTO clientRequestDTO, String clientId);
    ClientResponseDTO getClientById(String id);

    void deleteClientByClientId(String clientId);
}
