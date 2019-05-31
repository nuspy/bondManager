package io.bounds4all.bondsmanager.services;

import io.bounds4all.bondsmanager.configurations.security.JwtTokenProvider;
import io.bounds4all.bondsmanager.model.User;
import io.bounds4all.bondsmanager.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    public User findByToken(String token) {
        String email = jwtTokenProvider.getUsername(token);
        return userRepository.findByEmail(email);
    }

}
