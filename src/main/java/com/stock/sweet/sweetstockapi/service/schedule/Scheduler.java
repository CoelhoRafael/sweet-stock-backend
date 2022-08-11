package com.stock.sweet.sweetstockapi.service.schedule;

import com.stock.sweet.sweetstockapi.service.CompanyService;
import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class Scheduler {

    Logger logger = LoggerFactory.getLogger(Scheduler.class);

    @Autowired
    private CompanyService companyService;

    @Scheduled(cron = "0 0 3 * * *")
    public void changeCompaniesAssociateCode() {
        var companies = companyService.findAllCompanies();
        companies.stream().forEach(
                company -> company.setAssociateCode(RandomStringUtils.randomAlphanumeric(20))
        );
        logger.info("Changing associate code, of all companies " + LocalDateTime.now());
        companyService.saveAllCompanies(companies);
    }
}
