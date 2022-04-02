package com.stock.sweet.sweetstockapi.controller;

import com.stock.sweet.sweetstockapi.dto.request.CompanyRequest;
import com.stock.sweet.sweetstockapi.dto.response.CompanyResponse;
import com.stock.sweet.sweetstockapi.mapper.CompanyMapper;
import com.stock.sweet.sweetstockapi.service.CompanyService;
import com.stock.sweet.sweetstockapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    @Autowired
    private CompanyMapper companyMapper;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity createCompany(@RequestBody CompanyRequest body) {
        var company = companyService.createCompany(companyMapper.convertRequestToModel(body));
        var user = userService.createUser(companyMapper.convertRequestToUserModel(body, company.getId()));
        return ResponseEntity.status(201).build();
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CompanyResponse> getAllCompanies() {
        return companyMapper.convertModelListToResponseList(
                companyService.getAllCompanys()
        );
    }

    @GetMapping("/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public CompanyResponse getCompanyByUuid(@PathVariable String uuid) throws Exception {
        return companyMapper.convertModelToResponse(
                companyService.findCompanyByUuid(uuid)
        );
    }
}