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
    private Double buyValue;
    private Integer provideCode;
    private Integer numberLot;
    private String brand;
    private LocalDate purchaseDate;
    private String picture;
}
