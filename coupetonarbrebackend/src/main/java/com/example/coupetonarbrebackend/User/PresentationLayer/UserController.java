package com.example.coupetonarbrebackend.User.PresentationLayer;

import com.example.coupetonarbrebackend.User.BusinessLayer.ClientService;
import com.example.coupetonarbrebackend.User.DataLayer.Client;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("users")
public class UserController {
    private ClientService clientService;

    public UserController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/clients")
    public ResponseEntity<List<ClientResponseDTO>> getAllClients(){
        return ResponseEntity.ok().body(clientService.getAllClients());
    }

    @PutMapping("/clients/{clientId}")
    public ResponseEntity<ClientResponseDTO> updateClient(@PathVariable String clientId, @RequestBody ClientRequestDTO clientRequestDTO){
        return ResponseEntity.ok().body(clientService.updateClient(clientRequestDTO,clientId));
    }

    @GetMapping("/clients/{clientId}")
    public ResponseEntity<ClientResponseDTO> getClientById(@PathVariable String clientId){
        return ResponseEntity.ok().body(clientService.getClientById(clientId));
    }

    @PostMapping()
    public ResponseEntity<ClientResponseDTO> addClient(@RequestBody Client newClient) {
        return ResponseEntity.ok().body(clientService.addClient(newClient));
    }

    @DeleteMapping("/clients/{clientId}")
    public ResponseEntity<Void> deleteClientByClientId(@PathVariable String clientId) {
        clientService.deleteClientByClientId(clientId);
        return ResponseEntity.noContent().build();
    }
}

