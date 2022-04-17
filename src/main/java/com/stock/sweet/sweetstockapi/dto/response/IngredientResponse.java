package com.stock.sweet.sweetstockapi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class IngredientResponse {
    private String uuid;
    private String name;
    private String unitMeasurement;
    private BigDecimal quantity;
    private LocalDate expirationDate;
    private Double storageTemperature;
    private Boolean isRefigerated;
    private BigDecimal buyValue;
    private Integer provideCode;
    private BigDecimal quantityUsed;
    private Boolean viewInReports;
    private LocalDate dateInsert;
    private LocalDate dateUpdate;
}
