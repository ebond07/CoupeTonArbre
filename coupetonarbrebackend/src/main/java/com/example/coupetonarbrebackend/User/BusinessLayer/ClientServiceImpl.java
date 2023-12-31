package com.example.coupetonarbrebackend.User.BusinessLayer;

import com.example.coupetonarbrebackend.User.DataLayer.Client;
import com.example.coupetonarbrebackend.User.DataLayer.ClientRepository;

import com.example.coupetonarbrebackend.User.DataMapperLayer.ClientRequestMapper;
import com.example.coupetonarbrebackend.User.DataMapperLayer.ClientResponseMapper;
import com.example.coupetonarbrebackend.User.PresentationLayer.ClientRequestDTO;
import com.example.coupetonarbrebackend.User.PresentationLayer.ClientResponseDTO;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.util.List;

@Slf4j
@Service
public class ClientServiceImpl implements ClientService{

    private final ClientRepository clientRepository;

    private final ClientResponseMapper clientResponseMapper;

    private final ClientRequestMapper clientRequestMapper;

    private ClientRequestDTO clientRequestDTO;



    public ClientServiceImpl(ClientRepository clientRepository, ClientResponseMapper clientResponseMapper, ClientRequestMapper clientRequestMapper) {
        this.clientRepository = clientRepository;
        this.clientResponseMapper = clientResponseMapper;
        this.clientRequestMapper = clientRequestMapper;

    }

    @Override
    public ClientResponseDTO clientCreateProfile(ClientRequestDTO clientRequestDTO, String clientId) {

//        if (clientRepository.existsByUserId(userId))
//            throw new InvalidRequestException("Customer already exists");

        log.info("Creating client for clientId {}", clientId);
        log.info("Client request {}", clientRequestDTO);

        Client client = clientRequestMapper.requestModelToEntity(clientRequestDTO);

        client.setClientId(clientId);
        clientRepository.save(client);


        return clientResponseMapper.entityToResponseModel(client);
    }

    @Override
    public boolean checkIfClientExists(String clientId) {
        return clientRepository.existsByClientId(clientId);
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
    public ClientResponseDTO adminAddClient(Client newClient) {
        // Generate UUID
        newClient.setClientId(clientRequestDTO.generateUUIDString());
        // save client entity
        Client savedClient = clientRepository.save(newClient);
        return clientResponseMapper.entityToResponseModel(savedClient);
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

    @Override
    public ClientResponseDTO updateProfile(ClientRequestDTO clientRequestDTO, String clientId) {
        Client client = clientRepository.findClientByClientId(clientId);

        log.info("Updating profile for user {}", clientId);
        log.info("Client request {}", clientRequestDTO);

        client.setFirstName(clientRequestDTO.getFirstName());
        client.setLastName(clientRequestDTO.getLastName());
        client.setEmail(clientRequestDTO.getEmail() );
        client.setPhoneNumber(clientRequestDTO.getPhoneNumber());
        client.setAddress(clientRequestDTO.getAddress() );


        clientRepository.save(client);

        return clientResponseMapper.entityToResponseModel(client);
    }





//    @Override
//    public ClientResponseDTO updateClient(ClientRequestDTO clientRequestDTO) {
//        return clientResponseMapper.entityToResponseModel(clientRepository.save(clientRequest.requestDTOToEntity(clientRequestDTO)));
//    }
}


