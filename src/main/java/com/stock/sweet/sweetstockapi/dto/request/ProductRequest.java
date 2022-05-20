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
public class ProductRequest {
    private String name;
    private BigDecimal saleValue;
    private Date expirationDate;
    private LocalDate dateInsert;
    private LocalDate dateUpdate;
    private String category;
    private String picture;
}
