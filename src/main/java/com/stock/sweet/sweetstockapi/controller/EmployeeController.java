package com.stock.sweet.sweetstockapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.stock.sweet.sweetstockapi.dto.request.EmployeRequest;
import com.stock.sweet.sweetstockapi.dto.response.UserResponse;
import com.stock.sweet.sweetstockapi.mapper.EmployeeMapper;
import com.stock.sweet.sweetstockapi.model.Employee;
import com.stock.sweet.sweetstockapi.model.User;
import com.stock.sweet.sweetstockapi.service.EmployeeService;
import com.stock.sweet.sweetstockapi.utils.HeadersUtils;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("employees")
@CrossOrigin(origins = "*")
@SecurityRequirement(name = "bearerAuth")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private HeadersUtils headersUtils;

    @PostMapping
    public ResponseEntity createEmployee(@RequestBody EmployeRequest employeRequest) {
        employeeService.createUser(employeeMapper.convertRequestToModel(employeRequest), employeRequest.getAssociateCode());
        return ResponseEntity.status(201).build();
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllEmployees(@RequestHeader HttpHeaders headers) throws JsonProcessingException {
        var uuidCompany = headersUtils.getCompanyIdFromToken(headers.getFirst(HttpHeaders.AUTHORIZATION));

        return ResponseEntity.status(200).body(
                employeeMapper.convertModelListToResponseList(
                        employeeService.getAllUsers(uuidCompany)
                )
        );
    }

    @GetMapping("/not-aproved")
    public ResponseEntity<List<UserResponse>> getAllEmployeesNotAproved(@RequestHeader HttpHeaders headers) throws JsonProcessingException {
        var uuidCompany = headersUtils.getCompanyIdFromToken(headers.getFirst(HttpHeaders.AUTHORIZATION));

        return ResponseEntity.status(200).body(
                employeeMapper.convertModelListToResponseList(
                        employeeService.getUsersWaitingAcept(uuidCompany)
                )
        );
    }

    @PutMapping()
    public ResponseEntity employeesToAprove(
            @RequestHeader HttpHeaders headers,
            @RequestBody List<String> employeesToApprove
    ) throws JsonProcessingException {
        var uuidCompany = headersUtils.getCompanyIdFromToken(headers.getFirst(HttpHeaders.AUTHORIZATION));

        employeeService.toApproveEmployees(uuidCompany, employeesToApprove);

        return ResponseEntity.status(200).build();
    }
}
