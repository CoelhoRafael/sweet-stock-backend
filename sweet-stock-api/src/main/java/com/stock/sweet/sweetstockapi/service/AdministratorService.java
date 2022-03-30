package com.stock.sweet.sweetstockapi.service;

import com.stock.sweet.sweetstockapi.model.User;
import com.stock.sweet.sweetstockapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdministratorService implements Authentication {
    @Autowired
    private UserRepository userRepository;

    @Override
    public String login(String email, String password) {
        Optional<User> user = userRepository.findByEmailAndPassword(email, password);
        return user.map(User::getUuid).orElse(null);
    }

    @Override
    public User createUser(User user) {
        return null;
    }
}
