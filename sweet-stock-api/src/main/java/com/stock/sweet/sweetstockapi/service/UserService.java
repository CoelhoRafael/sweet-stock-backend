package com.stock.sweet.sweetstockapi.service;

import com.stock.sweet.sweetstockapi.model.User;
import com.stock.sweet.sweetstockapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public String loginUser(String email, String password) {
        Optional<User> user = userRepository.findByEmailAndPassword(email, password);
        if (user.isPresent()) {
            return user.get().getUuid();
        }
        return null;
    }
}
