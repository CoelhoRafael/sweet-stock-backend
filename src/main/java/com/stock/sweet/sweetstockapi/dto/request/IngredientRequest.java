package com.stock.sweet.sweetstockapi.dto.request;

import com.stock.sweet.sweetstockapi.controller.enums.UnitMeasurement;
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
    private UnitMeasurement unitMeasurement;
    private Integer numberUnits;
    private Double quantityPerUnit;
    private LocalDate expirationDate;
    private Double storageTemperature;
    private Boolean isRefigerated;
    private BigDecimal buyValue;
    private Integer provideCode;
    private Integer quantityUsed;
    private Integer numberLot;
    private String uuidCompany;
    private Double total;
    private String picture;
}
