package com.stock.sweet.sweetstockapi.dto.response.dashboard.chart;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IngredientDashboardResponse {
    private String uuid;
    private String name;
    private Integer amount;
}
