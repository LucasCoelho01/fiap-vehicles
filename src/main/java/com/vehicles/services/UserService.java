package com.vehicles.services;

import com.vehicles.entities.User;
import com.vehicles.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public void createUser(String login,  String password) {
        String encriptedPasswd=bCryptPasswordEncoder.encode(password);

        userRepository.save(new User(login, encriptedPasswd));
    }
}
