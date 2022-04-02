package com.stock.sweet.sweetstockapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.stock.sweet.sweetstockapi.security.HeadersUtils;
import com.stock.sweet.sweetstockapi.service.AccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accesses")
public class AccessController {

    @Autowired
    private AccessService accessService;

    @Autowired
    private HeadersUtils headersUtils;

    @PostMapping("/invite")
    public ResponseEntity sendAssociateCodeToEmail(@RequestHeader HttpHeaders headers,
                                                   @RequestParam String email) throws JsonProcessingException {

        String uuidCompany = headersUtils.getCompanyIdFromToken(headers.getFirst(HttpHeaders.AUTHORIZATION));

        accessService.sendInvite(email, uuidCompany);
        return ResponseEntity.status(200).build();
    }
}
