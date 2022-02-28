package com.stock.sweet.sweetstockapi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class IngredientRequest {
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
