package com.stock.sweet.sweetstockapi.controller;

import com.stock.sweet.sweetstockapi.dto.request.CompanyRequest;
import com.stock.sweet.sweetstockapi.dto.request.ProviderRequest;
import com.stock.sweet.sweetstockapi.dto.response.CompanyResponse;
import com.stock.sweet.sweetstockapi.dto.response.ProviderResponse;
import com.stock.sweet.sweetstockapi.mapper.CompanyMapper;
import com.stock.sweet.sweetstockapi.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companys")
public class CompanyController {

    @Autowired
    private CompanyMapper companyMapper;

    @Autowired
    private CompanyService companyService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CompanyResponse createCompany(@RequestBody CompanyRequest company) {
        return companyMapper.convertModelToResponse(
                companyService.createCompany(companyMapper.convertRequestToModel(company))
        );
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CompanyResponse> getAllCompanys() {
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
