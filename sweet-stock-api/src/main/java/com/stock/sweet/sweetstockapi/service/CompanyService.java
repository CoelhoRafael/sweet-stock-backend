package com.stock.sweet.sweetstockapi.service;

import com.stock.sweet.sweetstockapi.mapper.AddressMapper;
import com.stock.sweet.sweetstockapi.model.Company;
import com.stock.sweet.sweetstockapi.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.GregorianCalendar;
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
    public List<Company> getAllComapnysOpen(LocalTime hour){
        GregorianCalendar gc = new GregorianCalendar();
        int diaDaSemana = gc.get(GregorianCalendar.DAY_OF_WEEK);
        return companyRepository.findCompaniesByOpenHourAfterAndCloseHourBefore(hour, hour);
    }

    public Company findCompanyByUuid(String uuid) throws Exception {
        return companyRepository.findByUuid(uuid).orElseThrow(() -> new Exception("UUID não encontrado!"));
    }
}
