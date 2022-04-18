package com.stock.sweet.sweetstockapi.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CardsResponse {
    private Integer nearEndIngredients;
    private Integer productsSoldMonth;
    private Integer expiredIngredients;
    private Double monthExpenses;
}
