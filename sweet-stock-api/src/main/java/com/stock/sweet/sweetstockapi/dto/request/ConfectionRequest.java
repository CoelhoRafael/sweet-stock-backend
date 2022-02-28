package com.stock.sweet.sweetstockapi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ConfectionRequest {
    private LocalDate date;
    private BigDecimal quantityIngredient;
    private BigDecimal cost;
    private Integer employee;
}
