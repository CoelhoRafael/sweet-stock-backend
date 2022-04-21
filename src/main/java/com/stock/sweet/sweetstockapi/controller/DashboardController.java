package com.stock.sweet.sweetstockapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.stock.sweet.sweetstockapi.dto.response.dashboard.DashboardResponse;
import com.stock.sweet.sweetstockapi.mapper.DashboardMapper;
import com.stock.sweet.sweetstockapi.service.IngredientService;
import com.stock.sweet.sweetstockapi.service.OutStockService;
import com.stock.sweet.sweetstockapi.service.ProductService;
import com.stock.sweet.sweetstockapi.utils.HeadersUtils;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/dashboards")
@SecurityRequirement(name = "bearerAuth")
public class DashboardController {

    @Autowired
    private HeadersUtils headersUtils;

    @Autowired
    private DashboardMapper dashboardMapper;

    @Autowired
    private ProductService productService;

    @Autowired
    private IngredientService ingredientService;

    @Autowired
    private OutStockService outStockService;

    @GetMapping
    public ResponseEntity getDashboardData(@RequestHeader HttpHeaders headers) throws JsonProcessingException {
        String uuidCompany = headersUtils.getCompanyIdFromToken(headers.getFirst(HttpHeaders.AUTHORIZATION));

        DashboardResponse dashboardResponse = dashboardMapper.convertToDashboardResponse(
                ingredientService.getAllIngredientsNearExpire(uuidCompany),
                outStockService.getOutStockCurrentMonth(uuidCompany),
                ingredientService.getExpiredIngredients(uuidCompany),
                ingredientService.getIngredientsCurrentMonth(uuidCompany),
                ingredientService.getAllIngredients(uuidCompany),
                outStockService.getAllOutStockNonExpiredProduct(uuidCompany)
        );

        return ResponseEntity.status(200).body(dashboardResponse);
    }
}
