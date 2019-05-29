package io.bounds4all.bondsmanager.business_logic.authentication;

import io.bounds4all.bondsmanager.services.UserAuthenticationService;
import io.bounds4all.bondsmanager.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UUIDAuthenticationService implements UserAuthenticationService {
    @Autowired
    private ClientService userService;

    @Override
    public String login(String username, String password) {
        return userService.getByUsername(username)
                .filter(u -> u.getPassword().equals(password))
                .map(u -> {
                    u.setToken(UUID.randomUUID().toString());
                    userService.save(u);
                    return u.getToken();
                })
                .orElseThrow(() -> new BadCredentialsException("Invalid username or password."));
    }

    @Override
    public Client authenticateByToken(String token) {
        return userService.getByToken(token)
                .orElseThrow(() -> new BadCredentialsException("Token not found."));
    }

    @Override
    public void logout(String username) {
        userService.getByUsername(username)
                .ifPresent(u -> {
                    u.setToken(null);
                    userService.save(u);
                });
    }
}