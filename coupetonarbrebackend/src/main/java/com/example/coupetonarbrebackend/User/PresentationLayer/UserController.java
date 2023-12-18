package com.example.coupetonarbrebackend.User.PresentationLayer;

import com.example.coupetonarbrebackend.User.BusinessLayer.ClientService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.example.coupetonarbrebackend.User.DataLayer.Client;
import com.example.coupetonarbrebackend.User.DataLayer.ClientRepository;
import com.example.coupetonarbrebackend.User.DataMapperLayer.ClientRequestMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000",allowCredentials = "true", allowedHeaders = {"xsrf-token", "content-type"})
@RequestMapping("users")
public class UserController {
    private ClientService clientService;
    private final ClientRequestMapper clientRequestMapper;

    public UserController(ClientService clientService, ClientRequestMapper clientRequestMapper) {
        this.clientService = clientService;
        this.clientRequestMapper = clientRequestMapper;
    }



    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping("/clients")
    public ResponseEntity<List<ClientResponseDTO>> getAllClients(){
        return ResponseEntity.ok().body(clientService.getAllClients());
    }
//    @PreAuthorize("hasAuthority('Admin')")
    @PutMapping("/clients/{clientId}")
    public ResponseEntity<ClientResponseDTO> updateClient(@PathVariable String clientId, @RequestBody ClientRequestDTO clientRequestDTO){
        return ResponseEntity.ok().body(clientService.updateClient(clientRequestDTO,clientId));
    }
    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping("/clients/{clientId}")
    public ResponseEntity<ClientResponseDTO> getClientById(@PathVariable String clientId){
        return ResponseEntity.ok().body(clientService.getClientById(clientId));
    }

    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping("/clients")
    public ResponseEntity<ClientResponseDTO> addClient(@RequestBody ClientRequestDTO clientRequestDTO) {
        Client newClient = clientRequestMapper.requestModelToEntity(clientRequestDTO);
        ClientResponseDTO response = clientService.addClient(newClient);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }



    @DeleteMapping("/clients/{clientId}")
    public ResponseEntity<Void> deleteClientByClientId(@PathVariable String clientId) {
        clientService.deleteClientByClientId(clientId);
        return ResponseEntity.noContent().build();
    }
}

