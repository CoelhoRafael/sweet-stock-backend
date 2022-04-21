package com.stock.sweet.sweetstockapi.dto.response.dashboard;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CardResponse {
    private Integer nearEndIngredients;
    private Integer productsSoldMonth;
    private Integer expiredIngredients;
    private Double monthExpenses;
}
