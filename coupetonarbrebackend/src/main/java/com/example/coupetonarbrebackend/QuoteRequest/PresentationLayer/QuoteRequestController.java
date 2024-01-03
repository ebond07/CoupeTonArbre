package com.example.coupetonarbrebackend.QuoteRequest.PresentationLayer;

import com.example.coupetonarbrebackend.QuoteRequest.BusinessLayer.QuoteRequestService;
import com.example.coupetonarbrebackend.QuoteRequest.DataMapperLayer.QuoteRequestRequestMapper;
import com.example.coupetonarbrebackend.User.PresentationLayer.ClientRequestDTO;
import com.example.coupetonarbrebackend.User.PresentationLayer.ClientResponseDTO;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:3000",allowCredentials = "true", allowedHeaders = {"xsrf-token", "content-type"})
@RequestMapping("quoteRequests")
public class QuoteRequestController {
    private final QuoteRequestService quoteRequestService;
    private final QuoteRequestRequestMapper quoteRequestRequestMapper;

    public QuoteRequestController(QuoteRequestService quoteRequestService, QuoteRequestRequestMapper quoteRequestRequestMapper) {
        this.quoteRequestService = quoteRequestService;
        this.quoteRequestRequestMapper = quoteRequestRequestMapper;
    }

    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping()
    public ResponseEntity<List<QuoteRequestResponseDTO>> getAllQuoteRequests(){
        return ResponseEntity.ok().body(quoteRequestService.getAllQuoteRequests());
    }

//    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping()
    public ResponseEntity<QuoteRequestResponseDTO> adminCreateQuoteRequest(@RequestBody QuoteRequestRequestDTO quoteRequestRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(quoteRequestService.adminCreateQuoteRequest(quoteRequestRequestDTO));
    }

//    @PreAuthorize("hasAuthority('Client')")
    @PostMapping("/client")
    public ResponseEntity<QuoteRequestResponseDTO> clientCreateQuoteRequest(@AuthenticationPrincipal OidcUser principal,
                                                                 @Valid @RequestBody QuoteRequestRequestDTO quoteRequestRequestDTO) {
        String clientId = principal.getSubject();
        log.info("Client with clientId: {}", clientId);



        QuoteRequestResponseDTO quoteRequestResponseDTO = quoteRequestService.clientCreateQuoteRequest(quoteRequestRequestDTO, clientId);

        return ResponseEntity.status(HttpStatus.CREATED).body(quoteRequestResponseDTO);

    }
}
