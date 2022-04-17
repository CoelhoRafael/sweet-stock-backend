package com.stock.sweet.sweetstockapi.service;

import com.stock.sweet.sweetstockapi.model.Company;
import com.stock.sweet.sweetstockapi.model.Email;
import com.stock.sweet.sweetstockapi.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AccessService {
    @Autowired
    private EmailService emailService;

    @Autowired
    private CompanyRepository companyRepository;

    public void sendInvite(String email, String uuidCompany) {
        Company company = companyRepository.findByUuid(uuidCompany).orElse(null);

        if (company != null) {
            emailService.sendEmail(
                    new Email(
                            "sweet-stock",
                            "rafaelcoelho3110@gmail.com",
                            email,
                            "Token para cadastro de funcionário - SWEET-STOCK",
                            company.getAssociateCode(),
                            LocalDateTime.now()
                    )
            );
        }
    }
}
