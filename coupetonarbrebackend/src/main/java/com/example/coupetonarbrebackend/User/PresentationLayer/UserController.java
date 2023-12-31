package com.example.coupetonarbrebackend.User.PresentationLayer;

import com.example.coupetonarbrebackend.User.BusinessLayer.ClientService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.example.coupetonarbrebackend.User.DataLayer.Client;
import com.example.coupetonarbrebackend.User.DataMapperLayer.ClientRequestMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
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


    //Admin gets all clients
    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping("/clients")
    public ResponseEntity<List<ClientResponseDTO>> getAllClients(){
        return ResponseEntity.ok().body(clientService.getAllClients());
    }

    //Admin updates a client's information
    @PutMapping("/clients/{clientId}")
    public ResponseEntity<ClientResponseDTO> adminUpdateClient(@PathVariable String clientId, @RequestBody ClientRequestDTO clientRequestDTO){
        return ResponseEntity.ok().body(clientService.updateClient(clientRequestDTO,clientId));
    }

    //Admin gets a client's information by their id
    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping("/clients/{clientId}")
    public ResponseEntity<ClientResponseDTO> adminGetClientById(@PathVariable String clientId){
        return ResponseEntity.ok().body(clientService.getClientById(clientId));
    }

    //Client gets their profile information
    @PreAuthorize("hasAuthority('Client')")
    @GetMapping("/client")
    public ResponseEntity<ClientResponseDTO> clientGetProfile(@AuthenticationPrincipal OidcUser principal, @RequestParam Map<String, String> requestParams) {
        log.info("Get client with clientId: {}", principal.getSubject());

        if (requestParams.containsKey("simpleCheck") && requestParams.get("simpleCheck").equals("true")) {
            if(clientService.checkIfClientExists(principal.getSubject()))
                return ResponseEntity.ok().build();
            else
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        ClientResponseDTO clientResponseDTO = clientService.getClientById(principal.getSubject());

        return ResponseEntity.ok(clientResponseDTO);

    }

    //Admin adds a client to the system
    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping("/clients")
    public ResponseEntity<ClientResponseDTO> adminAddClient(@RequestBody ClientRequestDTO clientRequestDTO) {
        Client newClient = clientRequestMapper.requestModelToEntity(clientRequestDTO);
        ClientResponseDTO response = clientService.adminAddClient(newClient);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    //Client creates their profile
    @PreAuthorize("hasAuthority('Client')")
    @PostMapping("/client")
    public ResponseEntity<ClientResponseDTO> clientCreateProfile(@AuthenticationPrincipal OidcUser principal,
                                                                 @Valid @RequestBody ClientRequestDTO clientRequestDTO) {
        String clientId = principal.getSubject();
        log.info("Create client with clientId: {}", clientId);



        ClientResponseDTO clientResponseDTO = clientService.clientCreateProfile(clientRequestDTO, clientId);

        return ResponseEntity.status(HttpStatus.CREATED).body(clientResponseDTO);

    }

    //Client updates their profile
    @PutMapping("/client")
    @PreAuthorize("hasAnyAuthority('Client')")
    public ResponseEntity<ClientResponseDTO> updateProfile(@AuthenticationPrincipal OidcUser principal,
                                                           @Valid @RequestBody ClientRequestDTO clientRequestDTO) {
        String clientId = principal.getSubject();
        log.info("Update profile with clientId: {}", clientId);

        ClientResponseDTO clientResponseDTO = clientService.updateProfile(clientRequestDTO, clientId);

        return ResponseEntity.ok(clientResponseDTO);

    }


    //Admin deletes a clients profile
    @DeleteMapping("/clients/{clientId}")
    public ResponseEntity<Void> adminDeleteClientByClientId(@PathVariable String clientId) {
        clientService.deleteClientByClientId(clientId);
        return ResponseEntity.noContent().build();
    }
}

