package com.stock.sweet.sweetstockapi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class IngredientRequest {
    private String name;
    private String unitMeasurement;
    private BigDecimal quantity;
    private LocalDate expirationDate;
    private Double storageTemperature;
    private Boolean isRefigerated;
    private BigDecimal buyValue;
    private Integer provideCode;
    private BigDecimal quantityUsed;
    private Integer numberLot;
}
