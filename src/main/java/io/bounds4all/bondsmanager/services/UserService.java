package io.bounds4all.bondsmanager.services;

import io.bounds4all.bondsmanager.model.User;
import io.bounds4all.bondsmanager.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User findByToken(String token) {
       return userRepository.findByToken(token);
    }

}
