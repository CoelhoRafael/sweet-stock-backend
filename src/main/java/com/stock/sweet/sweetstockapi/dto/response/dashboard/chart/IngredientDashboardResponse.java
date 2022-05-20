package com.stock.sweet.sweetstockapi.dto.response.dashboard.chart;

import com.stock.sweet.sweetstockapi.controller.enums.UnitMeasurement;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IngredientDashboardResponse {
    private String uuid;
    private String name;
    private Integer amount;
    private String unitMeasurement;
}
