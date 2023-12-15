package com.example.coupetonarbrebackend.User.PresentationLayer;

import com.example.coupetonarbrebackend.User.BusinessLayer.ClientService;
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
    public ResponseEntity<ClientResponseDTO> updateClients(@PathVariable String clientId, @RequestBody ClientRequestDTO clientRequestDTO){
        return ResponseEntity.ok().body(clientService.updateClient(clientRequestDTO,clientId));
    }
}
