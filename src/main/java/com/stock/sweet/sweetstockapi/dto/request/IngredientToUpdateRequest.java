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
public class IngredientToUpdateRequest {
        private String name;
        private UnitMeasurement unitMeasurement;
        private BigDecimal quantity;
        private LocalDate expirationDate;
        private Double storageTemperature;
        private Boolean isRefigerated;
        private BigDecimal buyValue;
        private String provideCode;
        private Integer quantityUsed;
        private Double quantityPerUnit;
        private Double total;
        private Integer numberUnits;
        private String picture;
}
