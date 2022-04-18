package com.stock.sweet.sweetstockapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.stock.sweet.sweetstockapi.utils.HeadersUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/dashboards")
public class DashboardController {

    @Autowired
    private HeadersUtils headersUtils;
    @GetMapping
    public ResponseEntity getDashboardData(@RequestHeader HttpHeaders headers) throws JsonProcessingException {
        String uuidCompany = headersUtils.getCompanyIdFromToken(headers.getFirst(HttpHeaders.AUTHORIZATION));

    }
}
