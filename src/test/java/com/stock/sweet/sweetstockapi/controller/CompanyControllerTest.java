package com.stock.sweet.sweetstockapi.controller;

import com.stock.sweet.sweetstockapi.dto.request.CompanyRequest;
import com.stock.sweet.sweetstockapi.dto.response.CompanyResponse;
import com.stock.sweet.sweetstockapi.mapper.CompanyMapper;
import com.stock.sweet.sweetstockapi.model.Company;
import com.stock.sweet.sweetstockapi.repository.CompanyRepository;
import com.stock.sweet.sweetstockapi.service.CompanyService;
import com.stock.sweet.sweetstockapi.service.EmailService;
import com.stock.sweet.sweetstockapi.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
@SpringBootTest(classes = {CompanyController.class})
class CompanyControllerTest {
    @Autowired
    private CompanyController con;

    @MockBean
    private CompanyMapper map;

    @MockBean
    private CompanyService service;

    @MockBean
    private CompanyRepository rep;
    @MockBean
    private CompanyRequest categoryRequest;
    @MockBean
    private CompanyResponse res;
    @MockBean
    private UserService userService;

    @MockBean
    private EmailService emailService;

//    @Test
//    @DisplayName("Should create a company")
//    void createCompany(){
//        CompanyRequest req = mock(CompanyRequest.class);
//        when(req.getEmail()).thenReturn("test");
//        when(req.getEmail()).thenReturn("test");
//    }
    @Test
    @DisplayName("Should return all companies")
    void getAllCompanies() {
        Company c1 = mock(Company.class);
        Company c2 = mock(Company.class);
        CompanyResponse res1 = mock(CompanyResponse.class);
        List<Company> listMock= List.of(c1,c2);
        when(map.convertModelListToResponseList(listMock)).thenReturn(Collections.singletonList(res));

        when(service.getAllCompanys()).thenReturn(listMock);
        List<CompanyResponse> resposta =  con.getAllCompanies();
    }

    @Test
    @DisplayName("Should return company by uuid")
    void getCompanyByUuid() {
        Company c1 = mock(Company.class);
        Company c2 = mock(Company.class);
        CompanyResponse res1 = mock(CompanyResponse.class);
        Optional<Company> listMock= Optional.of(c1);
        String uuid = "40c5ad5c-3597-4190-b9a7-3c3d868acff9";
        when(rep.findByUuid(uuid)).thenReturn(listMock);
        when(map.convertModelToResponse(c1)).thenReturn(res1);
        try {
            when(con.getCompanyByUuid(uuid)).thenReturn(res1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            assertEquals(res1, con.getCompanyByUuid(uuid));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}