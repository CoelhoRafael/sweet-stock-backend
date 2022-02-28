package com.stock.sweet.sweetstockapi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class IngredientResponse {
    private String uuid;
    private String name;
    private String unitMeasurement;
    private BigDecimal quantity;
    private Date expirationDate;
    private Double storageTemperature;
    private Boolean isRefigerated;
    private BigDecimal buyValue;
    private Integer provideCode;
    private BigDecimal quantityUsed;
    private LocalDate dateInsert;
    private LocalDate dateUpdate;
}
