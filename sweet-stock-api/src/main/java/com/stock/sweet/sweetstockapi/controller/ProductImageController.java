package com.stock.sweet.sweetstockapi.controller;

import com.stock.sweet.sweetstockapi.service.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "*")
public class ProductImageController {
    @Autowired
    private ProductImageService productImageService;

    @PostMapping
    public ResponseEntity storeProductImage(@RequestParam("file") MultipartFile file) {
        try {
            productImageService.store(file);
            return ResponseEntity.status(201).build();
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
    }
}
