package com.stock.sweet.sweetstockapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.stock.sweet.sweetstockapi.dto.request.EmployeRequest;
import com.stock.sweet.sweetstockapi.dto.request.ProviderRequest;
import com.stock.sweet.sweetstockapi.dto.request.UserRequest;
import com.stock.sweet.sweetstockapi.dto.response.ProductResponse;
import com.stock.sweet.sweetstockapi.dto.response.ProviderResponse;
import com.stock.sweet.sweetstockapi.dto.response.UserResponse;
import com.stock.sweet.sweetstockapi.mapper.EmployeeMapper;
import com.stock.sweet.sweetstockapi.model.Employee;
import com.stock.sweet.sweetstockapi.model.User;
import com.stock.sweet.sweetstockapi.model.enums.LevelAccess;
import com.stock.sweet.sweetstockapi.service.EmployeeService;
import com.stock.sweet.sweetstockapi.utils.HeadersUtils;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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
    @GetMapping("/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity <UserResponse> getUserByUuid(@PathVariable String uuid) throws Exception {
        return ResponseEntity.status(200).body(employeeMapper.convertModelToResponse(employeeService.getUserByUuidAndId(uuid)));
    }

    @DeleteMapping("/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public UserResponse deleteUser(@PathVariable String uuid, Integer id) throws Exception {
        return employeeMapper.convertModelToResponse(
               employeeService.deleteUserByUuidAndId(uuid, id)
        );
    }

    @PutMapping("/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse updateUser(@PathVariable String uuid,Integer id, @RequestBody UserRequest body) throws Exception {
        return employeeMapper.convertModelToResponse(
                employeeService.updateUser(uuid,id,body)
        );
    }




    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllEmployees(@RequestHeader HttpHeaders headers) throws JsonProcessingException {
        var uuidCompany = headersUtils.getCompanyIdFromToken(headers.getFirst(HttpHeaders.AUTHORIZATION));

        return ResponseEntity.status(200).body(
                employeeMapper.convertModelListToResponseList(
                        employeeService.getAllUsers(LevelAccess.EMPLOYEE.name(), uuidCompany)
                )
        );
    }



    @GetMapping("/not-approved")
    public ResponseEntity<List<UserResponse>> getAllEmployeesNotAproved(@RequestHeader HttpHeaders headers) throws JsonProcessingException {
        var uuidCompany = headersUtils.getCompanyIdFromToken(headers.getFirst(HttpHeaders.AUTHORIZATION));

        return ResponseEntity.status(200).body(
                employeeMapper.convertModelListToResponseList(
                        employeeService.getUsersWaitingAcept(LevelAccess.EMPLOYEE.name(), uuidCompany)
                )
        );
    }

    @PatchMapping()
    public ResponseEntity employeesToAprove(@RequestHeader HttpHeaders headers, @RequestBody List<String> employeesToApprove) throws JsonProcessingException {
        var uuidCompany = headersUtils.getCompanyIdFromToken(headers.getFirst(HttpHeaders.AUTHORIZATION));

        employeeService.toApproveEmployees(uuidCompany, employeesToApprove);

        return ResponseEntity.status(200).build();
    }
}
