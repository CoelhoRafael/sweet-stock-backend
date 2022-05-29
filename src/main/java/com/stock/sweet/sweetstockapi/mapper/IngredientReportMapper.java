package com.stock.sweet.sweetstockapi.mapper;

import com.stock.sweet.sweetstockapi.dto.request.IngredientReportRequest;
import com.stock.sweet.sweetstockapi.dto.response.IngredientReportResponse;
import com.stock.sweet.sweetstockapi.model.IngredientReport;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class IngredientReportMapper {

    public IngredientReportResponse convertModelToResponse(IngredientReport ingredientReport) {
        return new IngredientReportResponse(
                ingredientReport.getUuid(),
                ingredientReport.getDateInsert(),
                ingredientReport.getQuantityDaysForGenerateReport(),
                ingredientReport.getIdCompany()
        );
    }

    public IngredientReport convertRequestToModel(IngredientReportRequest ingredientReportRequest) {
        return new IngredientReport(
                null,
                UUID.randomUUID().toString(),
                LocalDateTime.now(),
                ingredientReportRequest.getQuantityDaysForGenerateReport(),
                ingredientReportRequest.getArquivoTxt(),
                ingredientReportRequest.getIdCompany()
        );
    }
}
