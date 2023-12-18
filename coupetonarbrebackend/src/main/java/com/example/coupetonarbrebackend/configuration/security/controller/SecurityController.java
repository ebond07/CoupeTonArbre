package com.example.coupetonarbrebackend.configuration.security.controller;
import com.example.coupetonarbrebackend.configuration.security.models.UserInfoResponse;
import com.example.coupetonarbrebackend.configuration.security.service.Auth0LoginService;

import com.example.coupetonarbrebackend.configuration.security.service.Auth0ManagementService;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;

@Slf4j
@Generated
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cta/security")
public class SecurityController {


    private final Auth0LoginService auth0LoginService;
    private final Auth0ManagementService auth0ManagementService;

    @GetMapping("/redirect")
    public ResponseEntity<Void> redirectAfterLogin(@AuthenticationPrincipal OidcUser principal) throws IOException, InterruptedException {
        String accessToken;


        if (principal == null) {
            log.info("Principal is null");
            return ResponseEntity.status(HttpStatus.FOUND)
                    .location(URI.create("http://localhost:8080/oauth2/authorization/okta")).build();
        }


        //handle apple, google and facebook login
        if(principal.getSubject().contains("apple") || principal.getSubject().contains("google-oauth2")
                || principal.getSubject().contains("facebook")){
            return auth0LoginService.handleExternalLogin(principal);
        }

        if(principal.getSubject().contains("auth0")) {

            accessToken = auth0ManagementService.getAccessToken();


            boolean isVerified;

            try {
                isVerified = principal.getClaim("email_verified");
            } catch (NullPointerException e) {
                isVerified = false;
            }

            if (!principal.getClaims().containsKey("https://cta.com/roles")
                    || principal.getClaim("https://cta.com/roles").toString().equals("[]")) {
                log.info("No roles found, adding default role");

                if (!isVerified) {
                    log.info("User not verified, redirecting to verification page");
                    return ResponseEntity.status(HttpStatus.FOUND)
                            .location(URI.create("http://localhost:3000/verify")).build();
                }

                return ResponseEntity.status(HttpStatus.FOUND)
                        .location(URI.create("http://localhost:3000/logout")).build();

            }

            if (!isVerified) {
                log.info("User not verified, redirecting to verification page");
                auth0LoginService.sendVerificationEmail(principal.getSubject(), accessToken);
                return ResponseEntity.status(HttpStatus.FOUND)
                        .location(URI.create("http://localhost:3000/verify")).build();
            }
        }else{

            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .location(URI.create("http://localhost:3000/")).build();

        }

        log.info("Authorities: " + principal.getAuthorities().toString().replace(",","-"));
        return auth0LoginService.getVoidResponseEntity(principal);
    }

    @GetMapping("/user-info")
    public ResponseEntity<UserInfoResponse> getUserInfo(@AuthenticationPrincipal OidcUser principal) throws IOException, InterruptedException {

        if(principal == null)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();


        String userId = principal.getSubject();

        return ResponseEntity.ok().body(auth0ManagementService.getUserInfo(userId));
    }



}