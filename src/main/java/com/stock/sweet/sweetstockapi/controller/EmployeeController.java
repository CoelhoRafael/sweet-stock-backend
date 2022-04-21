package com.stock.sweet.sweetstockapi.controller;

import com.stock.sweet.sweetstockapi.dto.request.EmployeRequest;
import com.stock.sweet.sweetstockapi.mapper.EmployeeMapper;
import com.stock.sweet.sweetstockapi.service.EmployeeService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("employees")
@CrossOrigin(origins = "*")
@SecurityRequirement(name = "bearerAuth")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeMapper employeeMapper;

    @PostMapping
    public ResponseEntity createEmployee(@RequestBody EmployeRequest employeRequest) {
        employeeService.createUser(employeeMapper.convertRequestToModel(employeRequest), employeRequest.getAssociateCode());
        return ResponseEntity.status(201).build();
    }
}
