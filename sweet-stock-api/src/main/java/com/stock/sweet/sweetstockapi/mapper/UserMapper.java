package com.stock.sweet.sweetstockapi.mapper;

import com.stock.sweet.sweetstockapi.dto.request.UserRequest;
import com.stock.sweet.sweetstockapi.model.User;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.UUID;

@Component
public class UserMapper {
    public User convertRequestToModel(UserRequest userRequest) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        return new User(
                null,
                UUID.randomUUID().toString(),
                userRequest.getName(),
                userRequest.getEmail(),
                getEncryptedPassword(userRequest.getPassword()),
                userRequest.getTelephoneNumber(),
                userRequest.getLevelAccess().name()
        );
    }

    public String getEncryptedPassword(String password) {
        return Base64.getEncoder().encodeToString(password.getBytes(StandardCharsets.UTF_8));
    }


}
