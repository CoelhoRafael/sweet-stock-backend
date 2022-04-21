package com.stock.sweet.sweetstockapi.dto.response.dashboard;

import com.stock.sweet.sweetstockapi.dto.response.dashboard.chart.IngredientDashboardResponse;
import com.stock.sweet.sweetstockapi.dto.response.dashboard.chart.NearExpireIngredients;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class DashboardResponse {
    private CardResponse cards;
    private ChartResponse chart;
    private List<NearExpireIngredients> nearExpireIngredients;
    private List<IngredientDashboardResponse> nearEndIngredients;
}
