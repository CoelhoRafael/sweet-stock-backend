package com.stock.sweet.sweetstockapi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class IngredientReportResponse {
    private String uuid;
    private LocalDateTime dateInsert;
    private Integer quantityDaysForGenerateReport;
    private Integer idCompany;
}
