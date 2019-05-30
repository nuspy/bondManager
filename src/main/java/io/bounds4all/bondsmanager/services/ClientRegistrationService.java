package io.bounds4all.bondsmanager.services;

import io.bounds4all.bondsmanager.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientRegistrationService {
    @Autowired
    private ClientService clientService;
    @Autowired
    private ClientAuthenticationService clientAuthenticationService;

    public String register(String username, String password) throws IllegalArgumentException {
        clientService.getByUserName(username)
                .ifPresent(u -> {
                    throw new IllegalArgumentException("Username already in use.");
                });

        Client client = new Client();
        client.setUserName(username);
        client.setPassword(password);
        clientService.save(client);

        return clientAuthenticationService.login(username, password);
    }
}