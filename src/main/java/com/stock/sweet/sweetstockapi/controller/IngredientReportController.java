package com.stock.sweet.sweetstockapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.stock.sweet.sweetstockapi.dto.request.IngredientReportRequest;
import com.stock.sweet.sweetstockapi.mapper.IngredientReportMapper;
import com.stock.sweet.sweetstockapi.service.IngredientReportService;
import com.stock.sweet.sweetstockapi.utils.HeadersUtils;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("ingredients-reports")
@CrossOrigin(origins = "*")
@SecurityRequirement(name = "bearerAuth")
public class IngredientReportController {

    @Autowired
    private IngredientReportMapper ingredientReportMapper;

    @Autowired
    private IngredientReportService ingredientReportService;

    @Autowired
    private HeadersUtils headersUtils;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity createIngredientReport(@RequestBody IngredientReportRequest body) {
        String ingredientReport = ingredientReportService.createIngredientsReportForDays(body);

        ingredientReportMapper.convertModelToResponse(
                ingredientReportService.createIngredientReport(ingredientReportMapper.convertRequestToModel(body)));

        return ResponseEntity
                .status(200)
                .header("content-type", "text/csv")
                .header("content-disposition", "filename=\"relatorio-de-pets.csv\"")
                .body(ingredientReport);
    }

    @PostMapping("/import-txt")
    public ResponseEntity importTxt(@RequestBody String arquivoTxt) {
        ingredientReportService.saveTxt(arquivoTxt);

        return ResponseEntity.status(201).build();
    }
}
