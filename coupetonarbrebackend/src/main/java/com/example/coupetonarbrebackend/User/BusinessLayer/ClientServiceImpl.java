package com.example.coupetonarbrebackend.User.BusinessLayer;

import com.example.coupetonarbrebackend.User.DataLayer.ClientRepository;

import com.example.coupetonarbrebackend.User.DataMapperLayer.ClientResponseMapper;
import com.example.coupetonarbrebackend.User.PresentationLayer.ClientResponseDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
   // @Transactional
    public void deleteClientByClientId(String clientId) {
        clientRepository.deleteByClientId(clientId);
    }
}


