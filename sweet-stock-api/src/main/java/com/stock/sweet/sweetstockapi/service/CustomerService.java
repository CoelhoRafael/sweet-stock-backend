package com.stock.sweet.sweetstockapi.service;

import com.stock.sweet.sweetstockapi.dto.request.UserRequest;
import com.stock.sweet.sweetstockapi.model.User;
import com.stock.sweet.sweetstockapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomerService implements Authentication{
    @Autowired
    private UserRepository userRepository;

    @Override
    public String login(String email, String password) {
        return null;
    }

    @Override
    public User createUser(User user) {
        return null;
    }
}
