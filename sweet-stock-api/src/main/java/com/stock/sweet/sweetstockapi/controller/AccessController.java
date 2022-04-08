package com.stock.sweet.sweetstockapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.stock.sweet.sweetstockapi.mapper.EmployeeMapper;
import com.stock.sweet.sweetstockapi.utils.HeadersUtils;
import com.stock.sweet.sweetstockapi.service.AccessService;
import com.stock.sweet.sweetstockapi.service.EmployeeService;
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
    private EmployeeService employeeService;

    @Autowired
    private HeadersUtils headersUtils;

    @Autowired
    private EmployeeMapper employeeMapper;

    @PostMapping("/invite")
    public ResponseEntity sendAssociateCodeToEmail(
            @RequestHeader HttpHeaders headers,
            @RequestParam String email) throws JsonProcessingException {

        String uuidCompany = headersUtils.getCompanyIdFromToken(headers.getFirst(HttpHeaders.AUTHORIZATION));

        accessService.sendInvite(email, uuidCompany);
        return ResponseEntity.status(200).build();
    }

    @GetMapping
    public ResponseEntity getAllWaitingForApproval(
            @RequestHeader HttpHeaders headers) throws JsonProcessingException {

        String uuidCompany = headersUtils.getCompanyIdFromToken(headers.getFirst(HttpHeaders.AUTHORIZATION));

        return ResponseEntity.status(200).body(employeeMapper.convertListModelToResponse(
                employeeService.getAllWaitingApproval(uuidCompany)
        ));
    }
}
