package com.stock.sweet.sweetstockapi.controller;

import com.stock.sweet.sweetstockapi.dto.request.LoginRequest;
import com.stock.sweet.sweetstockapi.dto.request.UserRequest;
import com.stock.sweet.sweetstockapi.mapper.UserMapper;
import com.stock.sweet.sweetstockapi.model.User;
import com.stock.sweet.sweetstockapi.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.AccessType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private UserMapper userMapper;

//    @PostMapping("/login")
//    public ResponseEntity loginUser(@RequestBody LoginRequest loginRequest) throws UnsupportedEncodingException, NoSuchAlgorithmException {
//        String uuidUser = employeeService.login(loginRequest.getEmail(), userMapper.getEncryptedPassword(loginRequest.getPassword()));
//        boolean isAuthenticated = uuidUser != null;
//        if (isAuthenticated) {
//            return ResponseEntity.status(200).body(uuidUser);
//        }
//        return ResponseEntity.status(401).build();
//    }

    @PostMapping("/create")
    public ResponseEntity createUser(@RequestBody UserRequest userRequest) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        User user = employeeService.createUser(
                userMapper.convertRequestToModel(userRequest)
        );

        if (user != null) {
            return ResponseEntity.status(201).build();
        }
        return ResponseEntity.status(500).build();
    }
}
