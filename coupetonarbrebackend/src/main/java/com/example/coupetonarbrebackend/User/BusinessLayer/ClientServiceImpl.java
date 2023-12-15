package com.example.coupetonarbrebackend.User.BusinessLayer;

import com.example.coupetonarbrebackend.User.DataLayer.Client;
import com.example.coupetonarbrebackend.User.DataLayer.ClientRepository;

import com.example.coupetonarbrebackend.User.DataMapperLayer.ClientRequestMapper;
import com.example.coupetonarbrebackend.User.DataMapperLayer.ClientResponseMapper;
import com.example.coupetonarbrebackend.User.PresentationLayer.ClientRequestDTO;
import com.example.coupetonarbrebackend.User.PresentationLayer.ClientResponseDTO;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ClientServiceImpl implements ClientService{

    private  ClientRepository clientRepository;

    private  ClientResponseMapper clientResponseMapper;

    private ClientRequestMapper clientRequestMapper;



    public ClientServiceImpl(ClientRepository clientRepository, ClientResponseMapper clientResponseMapper, ClientRequestMapper clientRequestMapper) {
        this.clientRepository = clientRepository;
        this.clientResponseMapper = clientResponseMapper;
        this.clientRequestMapper = clientRequestMapper;

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

    @Override
    public ClientResponseDTO updateClient(ClientRequestDTO clientRequestDTO, String clientId) {
        Client existingClient = clientRepository.findByClientId(clientId);
        if (existingClient == null) {
            return null;
        }
        Client newClient = clientRequestMapper.requestModelToEntity(clientRequestDTO);
        newClient.setId(existingClient.getId());
        newClient.setClientId(existingClient.getClientId());

        return clientResponseMapper.entityToResponseModel(clientRepository.save(newClient));
    }

//    @Override
//    public ClientResponseDTO updateClient(ClientRequestDTO clientRequestDTO) {
//        return clientResponseMapper.entityToResponseModel(clientRepository.save(clientRequest.requestDTOToEntity(clientRequestDTO)));
//    }
}
