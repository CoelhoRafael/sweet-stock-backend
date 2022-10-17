package com.stock.sweet.sweetstockapi.dto.request;

import com.stock.sweet.sweetstockapi.controller.enums.UnitMeasurement;
import com.stock.sweet.sweetstockapi.model.Category;
import com.stock.sweet.sweetstockapi.model.Company;
import com.stock.sweet.sweetstockapi.model.enums.CategoryEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductRequest {
    private String name;
    private BigDecimal saleValue;
    private Date expirationDate;
    private Boolean isRefrigerated;
    private Category category;
    private Boolean sold;
    private Integer total;
    private UnitMeasurement unitMeasurement;
    private String picture;
    private List<ProductIngredientRequest> ingredients;
    private Company company;
}
