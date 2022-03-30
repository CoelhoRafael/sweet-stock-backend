package com.stock.sweet.sweetstockapi.service;

import com.stock.sweet.sweetstockapi.dto.request.UserRequest;
import com.stock.sweet.sweetstockapi.model.User;

public interface Authentication {
    String login(String email, String password);

    User createUser(User user);
}
