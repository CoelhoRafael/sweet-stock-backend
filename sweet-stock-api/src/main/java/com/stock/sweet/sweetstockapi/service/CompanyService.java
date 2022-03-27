package com.stock.sweet.sweetstockapi.service;

import com.stock.sweet.sweetstockapi.mapper.AddressMapper;
import com.stock.sweet.sweetstockapi.model.Company;
import com.stock.sweet.sweetstockapi.model.Provider;
import com.stock.sweet.sweetstockapi.repository.CompanyRepository;
import com.stock.sweet.sweetstockapi.repository.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private AddressMapper addressMapper;

//    public Company createCompany(Company company) {                   ERROOOOOO
//        return CompanyRepository.save(company);
//    }

//    public List<Company> getAllCompanys() {
//        return CompanyRepository;
//    }
//
//    public Company findCompanyByUuid(String uuid) throws Exception {
//        return CompanyRepository.findByUuid(uuid).orElseThrow(() -> new Exception("UUID não encontrado!"));
//    }
}
