package com.stock.sweet.sweetstockapi.dto.response;

import com.stock.sweet.sweetstockapi.controller.enums.UnitMeasurement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductResponse {
    private String uuid;
    private String name;
    private BigDecimal saleValue;
    private Date expirationDate;
    private LocalDate dateInsert;
    private LocalDate productedBy;
    private LocalDate dateUpdate;
    private Boolean isRefigerated;
    private Double total;
    private UnitMeasurement unitMeasurement;
    private String category;
    private String picture;
}
