package com.stock.sweet.sweetstockapi.dto.response.dashboard;

import com.stock.sweet.sweetstockapi.dto.response.dashboard.chart.IngredientDashboardResponse;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class NearExpireIngredients {
    private LocalDate date;
    private List<IngredientDashboardResponse> items;
}
