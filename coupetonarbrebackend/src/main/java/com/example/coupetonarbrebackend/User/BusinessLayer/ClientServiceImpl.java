package com.example.coupetonarbrebackend.User.BusinessLayer;

import com.example.coupetonarbrebackend.User.DataLayer.ClientRepository;
import com.example.coupetonarbrebackend.User.DataMapperLayer.ClientRequestMapper;
import com.example.coupetonarbrebackend.User.DataMapperLayer.ClientResponseMapper;
import com.example.coupetonarbrebackend.User.PresentationLayer.ClientResponseDTO;
import com.example.coupetonarbrebackend.User.utils.EntityDTOUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService{

    private ClientRepository clientRepository;

    private ClientResponseMapper clientResponseMapper;

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
}
