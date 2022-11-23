package com.stock.sweet.sweetstockapi.service;

import com.stock.sweet.sweetstockapi.exception.NotFoundException;
import com.stock.sweet.sweetstockapi.mapper.AddressMapper;
import com.stock.sweet.sweetstockapi.model.Company;
import com.stock.sweet.sweetstockapi.model.Product;
import com.stock.sweet.sweetstockapi.repository.CompanyRepository;
import com.stock.sweet.sweetstockapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    private ProductRepository productRepository;

    public String getAssociateCode(String uuidCompany) throws NotFoundException {
        Company company = companyRepository.findByUuid(uuidCompany).orElse(null);

        if (company != null) {
            return company.getAssociateCode();
        } else {
            throw new NotFoundException("Empresa não encontrada");
        }
    }

    public Company createCompany(Company company) {
        if (company == null){
            throw new IllegalArgumentException("tem que passar dados");
        }
        return companyRepository.save(company);
    }

    public List<Company> getAllCompanys() {
        return companyRepository.findAll();
    }

    public Company findCompanyByUuid(String uuid) throws Exception {
        return companyRepository.findByUuid(uuid).orElseThrow(() -> new Exception("UUID não encontrado!"));
    }

    public Boolean isOpen(Company c){
        LocalTime horas = LocalTime.now();
        String timeColonPattern = "HH:mm";
        DateTimeFormatter timeColonFormatter = DateTimeFormatter.ofPattern(timeColonPattern);
        timeColonFormatter.format(horas);
        DayOfWeek dia = LocalDate.now().getDayOfWeek();

        if (horas.isAfter(c.getHoursCompany().getHoraAbertura())){
         if (horas.isBefore(c.getHoursCompany().getHoraFechar())){
             return true;
         }
        }
     return false;
    }
    public List<Company>findCompanyIsOpen()throws Exception{
        List<Company> company =  companyRepository.findAll();

     return null;

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

    public List<Company> getAllActiveCompanies() {
        return companyRepository.findAllActiveCompanies();
    }

    public ResponseEntity<String> getNameCompany(String uuidProduct) {
        Product product = productRepository.getByUuid(uuidProduct);
        return ResponseEntity.ok().body(product.getCompany().getName());
    }
}
