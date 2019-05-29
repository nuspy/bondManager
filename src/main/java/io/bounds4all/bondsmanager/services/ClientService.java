package io.bounds4all.bondsmanager.services;


import io.bounds4all.bondsmanager.model.Client;
import io.bounds4all.bondsmanager.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    public Client save(Client client) {
        return clientRepository.save(client);
    }

    public Optional<Client> getByToken(String token) {
        return clientRepository.findByToken(token);
    }

    public Optional<Client> getByUsername(String username) {
        return clientRepository.findByUsername(username);
    }
}
