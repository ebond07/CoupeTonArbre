package com.example.coupetonarbrebackend.User.BusinessLayer;

import com.example.coupetonarbrebackend.User.DataLayer.ClientRepository;

import com.example.coupetonarbrebackend.User.DataMapperLayer.ClientResponseMapper;
import com.example.coupetonarbrebackend.User.PresentationLayer.ClientResponseDTO;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ClientServiceImpl implements ClientService{

    private  ClientRepository clientRepository;

    private  ClientResponseMapper clientResponseMapper;


    public ClientServiceImpl(ClientRepository clientRepository, ClientResponseMapper clientResponseMapper) {
        this.clientRepository = clientRepository;
        this.clientResponseMapper = clientResponseMapper;

    }
    @Override
    public List<ClientResponseDTO> getAllClients() {
        return clientResponseMapper.entityListToResponseModelList(clientRepository.findAll()) ;

    }

    @Override
    public ClientResponseDTO getClientById(String id) {
        return clientResponseMapper.entityToResponseModel(clientRepository.findClientByClientId(id));
    }
    @Override
    @Transactional
    public void deleteClientByClientId(String clientId) {
        clientRepository.deleteByClientId(clientId);
    }
}
