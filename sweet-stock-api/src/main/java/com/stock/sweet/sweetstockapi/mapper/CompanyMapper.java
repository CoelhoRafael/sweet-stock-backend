package com.stock.sweet.sweetstockapi.mapper;

import com.stock.sweet.sweetstockapi.dto.request.CompanyRequest;


import com.stock.sweet.sweetstockapi.dto.response.CompanyResponse;
import com.stock.sweet.sweetstockapi.dto.response.ProductResponse;
import com.stock.sweet.sweetstockapi.model.Company;
import com.stock.sweet.sweetstockapi.model.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class CompanyMapper {
    @Autowired
    private AddressMapper addressMapper;

    public Company convertRequestToModel(CompanyRequest companyRequest) {
        return new Company(
                null,
                UUID.randomUUID().toString(),
                companyRequest.getName(),
                companyRequest.getFantasyName(),
                companyRequest.getCeo(),
                companyRequest.getCpf(),
                companyRequest.getCnpj(),
                companyRequest.getEmail(),
                companyRequest.getTelephoneNumber()
        );
    }

    public CompanyResponse convertModelToResponse(Company company) {
        return new CompanyResponse(
                company.getUuid(),
                company.getName(),
                company.getFantansyName(),
                company.getCeo(),
                company.getCpf(),
                company.getCnpj(),
                company.getEmail(),
                company.getTelephoneNumber()

                );
    }

    public List<CompanyResponse> convertModelListToResponseList(List<Company> company) {
        return company.stream().map(c -> {
            return new CompanyResponse(
                    c.getUuid(),
                    c.getName(),
                    c.getFantansyName(),
                    c.getCeo(),
                    c.getCpf(),
                    c.getCnpj(),
                    c.getEmail(),
                    c.getTelephoneNumber()

            );
        }).collect(Collectors.toList());
    }

}
