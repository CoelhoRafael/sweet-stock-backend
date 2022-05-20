package com.stock.sweet.sweetstockapi.dto.response;

import com.stock.sweet.sweetstockapi.controller.enums.UnitMeasurement;
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
    private UnitMeasurement unitMeasurement;
    private Integer numberUnits;
    private Double quantityPerUnit;
    private LocalDate expirationDate;
    private Double storageTemperature;
    private Boolean isRefigerated;
    private BigDecimal buyValue;
    private Integer provideCode;
    private Integer quantityUsed;
    private LocalDate dateInsert;
    private LocalDate dateUpdate;
    private Integer numberLot;
    private Boolean expirated;
    private Boolean viewInReports;
    private String uuidCompany;
    private Double total;
}
