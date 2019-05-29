package io.bounds4all.bondsmanager.services;

import io.bounds4all.bondsmanager.model.Client;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public interface UserAuthenticationService {
    String login(String username, String password) throws BadCredentialsException;

    Client authenticateByToken(String token) throws AuthenticationException;

    void logout(String username);

}
