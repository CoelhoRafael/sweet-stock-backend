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
public class ProductRequestDashboard {
    private String name;
    private BigDecimal saleValue;
    private LocalDate productedBy;
    private Boolean isRefigerated;
    private Double total;
    private UnitMeasurement unitMeasurement;
    private String picture;

}
