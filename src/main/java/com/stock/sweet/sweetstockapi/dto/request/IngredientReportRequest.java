package com.stock.sweet.sweetstockapi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class IngredientReportRequest {
    private Integer quantityDaysForGenerateReport;
    private Integer idCompany;
    private String arquivoTxt;
}
