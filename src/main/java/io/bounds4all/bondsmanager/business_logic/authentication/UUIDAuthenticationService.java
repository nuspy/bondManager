package io.bounds4all.bondsmanager.business_logic.authentication;

import io.bounds4all.bondsmanager.services.ClientService;
import io.bounds4all.bondsmanager.services.ClientAuthenticationService;
import io.bounds4all.bondsmanager.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UUIDAuthenticationService implements ClientAuthenticationService {
    @Autowired
    private ClientService clientService;

    @Override
    public String login(String username, String password) {
        return clientService.getByUserName(username)
                .filter(u -> u.getPassword().equals(password))
                .map(u -> {
                    u.setToken(UUID.randomUUID().toString());
                    clientService.save(u);
                    return u.getToken();
                })
                .orElseThrow(() -> new BadCredentialsException("Invalid username or password."));
    }

    @Override
    public Client authenticateByToken(String token) {
        return clientService.getByToken(token)
                .orElseThrow(() -> new BadCredentialsException("Token not found."));
    }

    @Override
    public void logout(String username) {
        clientService.getByUserName(username)
                .ifPresent(u -> {
                    u.setToken(null);
                    clientService.save(u);
                });
    }
}