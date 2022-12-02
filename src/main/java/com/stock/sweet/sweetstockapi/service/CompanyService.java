package com.stock.sweet.sweetstockapi.service;

import com.stock.sweet.sweetstockapi.exception.NotFoundException;
import com.stock.sweet.sweetstockapi.mapper.AddressMapper;
import com.stock.sweet.sweetstockapi.model.Company;
import com.stock.sweet.sweetstockapi.model.Product;
import com.stock.sweet.sweetstockapi.repository.CompanyRepository;
import com.stock.sweet.sweetstockapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

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

    public Boolean isOpen(Company c) {
        LocalTime horas = LocalTime.now();
        String timeColonPattern = "HH:mm";
        DateTimeFormatter timeColonFormatter = DateTimeFormatter.ofPattern(timeColonPattern);
        timeColonFormatter.format(horas);

        var numeroDiaSemana = LocalDate.now().getDayOfWeek().getValue();
        c.getHoursCompany();

        if (numeroDiaSemana == 0) {
            var isOpenSunday = c.getHoursCompany().getSunday();
            if(isOpenSunday && (horas.isAfter(LocalTime.parse(c.getHoursCompany().getHoraAbertura())) && (horas.isBefore(LocalTime.parse(c.getHoursCompany().getHoraFechar()))))){
                c.setIsOpen(true);
                return true;

            }else {
                c.setIsOpen(false);
                return false;
            }
        } else if (numeroDiaSemana == 1) {
            var isOpen = c.getHoursCompany().getMonday();
            if(isOpen && (horas.isAfter(LocalTime.parse(c.getHoursCompany().getHoraAbertura())) && (horas.isBefore(LocalTime.parse(c.getHoursCompany().getHoraFechar()))))){
                c.setIsOpen(true);
                return true;
            }else {
                c.setIsOpen(false);
                return false;
            }

        } else if (numeroDiaSemana == 2) {
            var isOpen = c.getHoursCompany().getTuesday();
            if(isOpen && (horas.isAfter(LocalTime.parse(c.getHoursCompany().getHoraAbertura())) && (horas.isBefore(LocalTime.parse(c.getHoursCompany().getHoraFechar()))))){
                c.setIsOpen(true);
                return true;
            }else {
                c.setIsOpen(false);
                return false;
            }

        } else if (numeroDiaSemana == 3) {
            var isOpen = c.getHoursCompany().getWednesday();
            if(isOpen && (horas.isAfter(LocalTime.parse(c.getHoursCompany().getHoraAbertura())) && (horas.isBefore(LocalTime.parse(c.getHoursCompany().getHoraFechar()))))){
                c.setIsOpen(true);
                return true;
            }else {
                c.setIsOpen(false);
                return false;
            }

        } else if (numeroDiaSemana == 4) {
            var isOpen = c.getHoursCompany().getThursday();
            if(isOpen && (horas.isAfter(LocalTime.parse(c.getHoursCompany().getHoraAbertura())) && (horas.isBefore(LocalTime.parse(c.getHoursCompany().getHoraFechar()))))){
                c.setIsOpen(true);
                return true;
            }else {
                c.setIsOpen(false);
                return false;
            }

        } else if (numeroDiaSemana == 5) {
            var isOpen = c.getHoursCompany().getFriday();
            if(isOpen && (horas.isAfter(LocalTime.parse(c.getHoursCompany().getHoraAbertura())) && (horas.isBefore(LocalTime.parse(c.getHoursCompany().getHoraFechar()))))){
                c.setIsOpen(true);
                return true;
            }else {
                c.setIsOpen(false);
                return false;
            }

        } else if (numeroDiaSemana == 6) {
            var isOpen = c.getHoursCompany().getSaturday();
            if(isOpen && (horas.isAfter(LocalTime.parse(c.getHoursCompany().getHoraAbertura())) && (horas.isBefore(LocalTime.parse(c.getHoursCompany().getHoraFechar()))))){
                c.setIsOpen(true);
                return true;
            }else {
                c.setIsOpen(false);
                return false;
            }

        }

        return false;
    }
    public Boolean findCompanyIsOpen(String uuid)throws Exception{
       Company company = companyRepository.findCompanyByUuid(uuid);

        return isOpen(company);





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

    public ResponseEntity<String> getNameCompanyJwt(String uuidCompany){
        Company company = companyRepository.findByUuid(uuidCompany).get();
        return ResponseEntity.ok().body(company.getName());
    }
}
