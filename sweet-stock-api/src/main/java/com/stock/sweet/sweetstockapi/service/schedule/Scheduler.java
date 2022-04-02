package com.stock.sweet.sweetstockapi.service.schedule;

import com.stock.sweet.sweetstockapi.service.CompanyService;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {
    @Autowired
    private CompanyService companyService;

    //    @Scheduled(cron = "0 0 * * * *")
    @Scheduled(cron = "0 */3 * * * *")
    public void changeCompaniesAssociateCode() {
        var companies = companyService.findAllCompanies();
        companies.stream().forEach(
                company -> company.setAssociateCode(RandomStringUtils.randomAlphanumeric(20))
        );
        System.out.println("Changing associate code, of all companies");
        companyService.saveAllCompanies(companies);
    }
}
