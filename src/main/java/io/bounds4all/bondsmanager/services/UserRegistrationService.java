package io.bounds4all.bondsmanager.services;

import io.bounds4all.bondsmanager.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRegistrationService {
    @Autowired
    private UserService userService;
    @Autowired
    private UserAuthenticationService authenticationService;

    public String register(String username, String password) throws IllegalArgumentException {
        userService.getByUsername(username)
                .ifPresent(u -> {
                    throw new IllegalArgumentException("Username already in use.");
                });

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        userService.save(user);

        return authenticationService.login(username, password);
    }
}
