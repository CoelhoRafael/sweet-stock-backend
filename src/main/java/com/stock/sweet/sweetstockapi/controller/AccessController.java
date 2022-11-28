package com.stock.sweet.sweetstockapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.stock.sweet.sweetstockapi.dto.response.app.home.HomeResponse;
import com.stock.sweet.sweetstockapi.mapper.EmployeeMapper;
import com.stock.sweet.sweetstockapi.service.AccessService;
import com.stock.sweet.sweetstockapi.service.CompanyService;
import com.stock.sweet.sweetstockapi.service.EmailService;
import com.stock.sweet.sweetstockapi.service.EmployeeService;
import com.stock.sweet.sweetstockapi.service.app.HomeService;
import com.stock.sweet.sweetstockapi.utils.HeadersUtils;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.context.Context;

@RestController
@RequestMapping("/accesses")
@CrossOrigin(origins = "*")
@SecurityRequirement(name = "bearerAuth")
public class AccessController {

    @Autowired
    private AccessService accessService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private HeadersUtils headersUtils;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private EmailService emailService;

    @PostMapping("/invite")
    public ResponseEntity sendAssociateCodeToEmail(
            @RequestHeader HttpHeaders headers,
            @RequestParam String email, String uuidCompany) throws Exception {


        var company = companyService.findCompanyByUuid(
                headersUtils.getCompanyIdFromToken(headers));

        Context ctx = new Context(LocaleContextHolder.getLocale());
        ctx.setVariable("company_name", company.getName());
        ctx.setVariable("code", company.getAssociateCode());

        var isSucessful = emailService.sendHtmlEmail(
                email,
                "Código de acesso recebido",
                "email_codigo_empresa",
                ctx
        );
//test
        if (isSucessful) {
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(500).build();
    }


    @GetMapping
    public ResponseEntity getAllWaitingForApproval(
            @RequestHeader HttpHeaders headers) throws JsonProcessingException {

        String uuidCompany = headersUtils.getCompanyIdFromToken(headers);

        return ResponseEntity.status(200).body(employeeMapper.convertListModelToResponse(
                employeeService.getAllWaitingApproval(uuidCompany)
        ));
    }

}
