package com.stock.sweet.sweetstockapi.service.app;

import com.stock.sweet.sweetstockapi.dto.response.address.AddressResponse;
import com.stock.sweet.sweetstockapi.dto.response.CategoryResponse;
import com.stock.sweet.sweetstockapi.dto.response.app.home.CompanyHomeResponse;
import com.stock.sweet.sweetstockapi.dto.response.app.home.HomeResponse;
import com.stock.sweet.sweetstockapi.model.Company;
import com.stock.sweet.sweetstockapi.service.CategoryService;
import com.stock.sweet.sweetstockapi.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HomeService {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CompanyService companyService;

    public ResponseEntity<HomeResponse> getHome() {
        List<CategoryResponse> categoriesResponseList = new ArrayList<>();
        ResponseEntity<List<CategoryResponse>> categoriesResponseListResponse = categoryService.getAllCategories();
        if (categoriesResponseListResponse.getStatusCodeValue() == 200) {
            categoriesResponseListResponse.getBody().forEach(categoryResponse -> {
                categoriesResponseList.add(categoryResponse);
            });
        }

        List<Company> companyList = companyService.getAllActiveCompanies();
        List<CompanyHomeResponse> companyHomeResponses = new ArrayList<>();

        companyList.forEach(company -> {
            companyHomeResponses.add(
                    new CompanyHomeResponse(
                            company.getUuid(),
                            company.getName(),
                            company.getFantansyName(),
                            company.getEmail(),
                            company.getTelephoneNumber(),
                            company.getPicture(),
                            company.getRating(),
                            new AddressResponse(
                                    company.getAddress().getUuid(),
                                    company.getAddress().getStreet(),
                                    company.getAddress().getNumber(),
                                    company.getAddress().getComplement(),
                                    company.getAddress().getCity(),
                                    company.getAddress().getState(),
                                    company.getAddress().getNeighborhood()
                            )
                    )
            );
        });

        if (categoriesResponseList.isEmpty() && companyHomeResponses.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(200).body(new HomeResponse(
                categoriesResponseList,
                companyHomeResponses
        ));
    }
}
