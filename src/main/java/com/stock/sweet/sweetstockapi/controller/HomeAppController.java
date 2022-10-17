package com.stock.sweet.sweetstockapi.controller;

import com.stock.sweet.sweetstockapi.dto.response.app.home.HomeResponse;
import com.stock.sweet.sweetstockapi.service.app.HomeService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/home")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = RequestMethod.POST)
@SecurityRequirement(name = "bearerAuth")
public class HomeAppController {

    @Autowired
    private HomeService homeService;

    @GetMapping
    public ResponseEntity<HomeResponse> getHome() {
        return homeService.getHome();
    }
}