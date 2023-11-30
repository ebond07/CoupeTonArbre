package com.example.coupetonarbrebackend.User.BusinessLayer;

import com.example.coupetonarbrebackend.User.PresentationLayer.ClientResponseDTO;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

public interface ClientService {

    List<ClientResponseDTO> getAllClients();
}
