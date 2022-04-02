package com.stock.sweet.sweetstockapi.service;

import com.stock.sweet.sweetstockapi.mapper.AddressMapper;
import com.stock.sweet.sweetstockapi.model.Company;
import com.stock.sweet.sweetstockapi.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private AddressMapper addressMapper;

    public Company createCompany(Company company) {
        return companyRepository.save(company);
    }

    public List<Company> getAllCompanys() {

        return companyRepository.findAll();
    }

    public Company findCompanyByUuid(String uuid) throws Exception {
        return companyRepository.findByUuid(uuid).orElseThrow(() -> new Exception("UUID não encontrado!"));
    }

    public Company findCompanyById(Integer id) throws Exception {
        return companyRepository.findById(id).orElseThrow(() -> new Exception("id não encontrado!"));
    }

    public List<Company> findAllCompanies() {
        return companyRepository.findAll();
    }

    public List<Company> saveAllCompanies(List<Company> companies) {
        return companyRepository.saveAll(companies);
    }
}
