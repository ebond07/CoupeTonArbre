package com.example.coupetonarbrebackend.User.BusinessLayer;

import com.example.coupetonarbrebackend.User.PresentationLayer.ClientRequestDTO;
import com.example.coupetonarbrebackend.User.DataLayer.Client;
import com.example.coupetonarbrebackend.User.PresentationLayer.ClientResponseDTO;


import java.util.List;

public interface ClientService {

    List<ClientResponseDTO> getAllClients();

    ClientResponseDTO getClientById(String id);

    ClientResponseDTO addClient(ClientRequestDTO clientRequestDTO);

    ClientResponseDTO updateClient(ClientRequestDTO clientRequestDTO, String clientId);

    void deleteClientByClientId(String clientId);
}
