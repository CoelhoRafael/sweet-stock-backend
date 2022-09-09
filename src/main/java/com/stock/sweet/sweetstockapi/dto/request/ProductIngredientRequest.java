package com.stock.sweet.sweetstockapi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductIngredientRequest {
    private String uuidIngredient;
    private Integer quantity;
}
