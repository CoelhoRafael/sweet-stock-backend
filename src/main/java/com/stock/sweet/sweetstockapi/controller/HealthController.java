package com.stock.sweet.sweetstockapi.controller;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
@CrossOrigin(origins = "*")
@SecurityRequirement(name = "bearerAuth")
public class HealthController {

    @GetMapping
    public ResponseEntity<String> health(){
        return ResponseEntity.ok("Api sweet stock funcionando corretamente!");
    }
}
