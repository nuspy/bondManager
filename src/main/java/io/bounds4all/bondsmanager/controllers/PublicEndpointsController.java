package io.bounds4all.bondsmanager.controllers;

import io.bounds4all.bondsmanager.services.ClientAuthenticationService;
import io.bounds4all.bondsmanager.services.ClientRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@RestController
public class PublicEndpointsController {

        @Autowired
        private ClientRegistrationService clientRegistrationService;
        @Autowired
        private ClientAuthenticationService clientAuthenticationService;

        @PostMapping("/register")
        public Object register(
                @RequestParam("username") String username,
                @RequestParam("password") String password) {
            try {
                return clientRegistrationService
                        .register(username, password);
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }

        @PostMapping("/login")
        public Object login(
                @RequestParam("username") String username,
                @RequestParam("password") String password) {
            try {
                return clientAuthenticationService
                        .login(username, password);
            } catch (BadCredentialsException e) {
                return ResponseEntity.status(UNAUTHORIZED).body(e.getMessage());
            }
        }

}
