package com.example.coupetonarbrebackend.User.BusinessLayer;

import com.example.coupetonarbrebackend.User.PresentationLayer.ClientRequestDTO;
import com.example.coupetonarbrebackend.User.DataLayer.Client;
import com.example.coupetonarbrebackend.User.PresentationLayer.ClientResponseDTO;


import java.util.List;

public interface ClientService {

    List<ClientResponseDTO> getAllClients();

    ClientResponseDTO getClientById(String id);

    ClientResponseDTO adminAddClient(Client newClient);

    ClientResponseDTO clientCreateProfile(ClientRequestDTO clientRequestDTO, String clientId);

    boolean checkIfClientExists(String clientId);



    ClientResponseDTO updateClient(ClientRequestDTO clientRequestDTO, String clientId);

    void deleteClientByClientId(String clientId);
}
