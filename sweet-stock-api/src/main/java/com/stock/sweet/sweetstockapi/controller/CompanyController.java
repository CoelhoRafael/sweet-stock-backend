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

import java.time.LocalTime;
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
        userService.createUser(companyMapper.convertRequestToUserModel(body));
        companyService.createCompany(companyMapper.convertRequestToModel(body));
        return ResponseEntity.status(201).build();
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CompanyResponse> getAllCompanies() {
        return companyMapper.convertModelListToResponseList(
                companyService.getAllCompanys()
        );
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CompanyResponse> getAllCompaniesOpen(LocalTime hour) {
        return companyMapper.convertModelListToResponseList(
                companyService.getAllComapnysOpen(hour)
        );
    }

    @GetMapping("/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public CompanyResponse getCompanyByUuid(@PathVariable String uuid) throws Exception {
        return companyMapper.convertModelToResponse(
                companyService.findCompanyByUuid(uuid)
        );
    }

//    @PutMapping("/{uuid}")
//    @ResponseStatus(HttpStatus.OK)
//    public CompanyResponse updateCompany(@PathVariable String uuid, @RequestBody CompanyRequest body) throws Exception {
//        return companyMapper.convertModelToResponse(
//                companyService.updateCompany(uuid, body)
//        );
//    }

//    @DeleteMapping("/{uuid}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public CompanyResponse deleteCompany(@PathVariable String uuid) throws Exception {
//        return companyMapper.convertModelToResponse(
//                companyService.deleteProvider(uuid)
//        );
//    }
}
