package com.stock.sweet.sweetstockapi.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IngredientDashboardResponse {
    private String name;
    private Integer quantity;

}
