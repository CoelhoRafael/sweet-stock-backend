package com.stock.sweet.sweetstockapi.service;

import com.stock.sweet.sweetstockapi.exception.NotFoundException;
import com.stock.sweet.sweetstockapi.mapper.AddressMapper;
import com.stock.sweet.sweetstockapi.model.Company;
import com.stock.sweet.sweetstockapi.repository.CompanyRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@SpringBootTest(classes = {CompanyService.class})
    class CompanyServiceTest {
        @Autowired
        private CompanyService service;

        @MockBean
        private CompanyRepository rep;

    @MockBean
    private AddressMapper addressMapper;


        @Test
        @DisplayName("Should Return associate code")
    void getAssociateCode() {
            Company c1 = mock(Company.class);
//            Company c2 = mock(Company.class);
            Optional<Company> listMock= Optional.of(c1);
            String uuid = "40c5ad5c-3597-4190-b9a7-3c3d868acff9";
            when(rep.findByUuid(uuid)).thenReturn(listMock);
            try {
                assertEquals( c1.getAssociateCode(),service.getAssociateCode(uuid));
            } catch (NotFoundException e) {
                e.printStackTrace();
            }

        }
    @Test
    @DisplayName("Should Return Null")
    void getAssociateCodeError() {
        Company c1 = mock(Company.class);
//            Company c2 = mock(Company.class);
        Optional<Company> listMock= Optional.of(c1);
        String uuid = "40c5ad5c-3597-4190-b9a7-3c3d868acff9";
        when(rep.findByUuid(uuid)).thenReturn(listMock);
        try {
            assertEquals( null ,service.getAssociateCode(null));
        } catch (NotFoundException e) {
            e.printStackTrace();
        }

    }

    @Test
    @DisplayName("Should Return company")
    void createCompany() {
        Company c1 = mock(Company.class);
        when(rep.save(c1)).thenReturn(c1);
        assertEquals(c1, service.createCompany(c1));
    }

    @Test
    @DisplayName("Should Return exception")
    void createCompanyError() {
        Company c1 = mock(Company.class);
        when(rep.save(c1)).thenReturn(null);
        assertThrows(IllegalArgumentException.class, () -> {
            service.createCompany(null);
        });

    }

    @Test
    @DisplayName("Should Return list of all companies")
    void getAllCompanys() {
        Company c1 = mock(Company.class);
        Company c2 = mock(Company.class);
        List<Company> listMock= List.of(c1,c2);
        when(rep.findAll()).thenReturn(listMock);
        assertEquals(listMock, service.getAllCompanys());

    }

    @Test
    @DisplayName("Should Return list empty")
    void getAllCompanysListEmpty() {
            when(rep.findAll()).thenReturn(null);
        assertEquals(null, service.getAllCompanys());

    }

    @Test
    @DisplayName("Should Return company by uuid")
    void findCompanyByUuid() {
        Company c1 = mock(Company.class);
        Optional<Company> listMock= Optional.of(c1);
        String uuid = "40c5ad5c-3597-4190-b9a7-3c3d868acff9";
        when(rep.findByUuid(uuid)).thenReturn(listMock);
        try {
            assertEquals(c1, service.findCompanyByUuid(uuid));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    @DisplayName("Should Return exception")
    void findCompanyByUuidError() {
        Company c1 = mock(Company.class);
        Optional<Company> listMock= Optional.of(c1);
        String uuid = "40c5ad5c-3597-4190-b9a7-3c3d868acff9";
        String uuidFalse = "dsadfasfsd";
        when(rep.findByUuid(uuid)).thenReturn(listMock);
        assertThrows(Exception.class, () -> {
            service.findCompanyByUuid(uuidFalse);
        });
    }

    @Test
    @DisplayName("Should Return company by id")
    void findCompanyById() {
        Company c1 = mock(Company.class);
        Optional<Company> listMock= Optional.of(c1);
        Integer id = 40;
        when(rep.findById(id)).thenReturn(listMock);
        try {
            assertEquals(c1, service.findCompanyById(id));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Should Return exception")
    void findCompanyByIdError() {
        Company c1 = mock(Company.class);
        Optional<Company> listMock= Optional.of(c1);
        Integer id = 40;
        Integer idFake = 50;
        when(rep.findById(id)).thenReturn(listMock);
        assertThrows(Exception.class, () -> {
            service.findCompanyById(idFake );
        });
    }



}