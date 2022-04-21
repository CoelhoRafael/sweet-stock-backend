package com.stock.sweet.sweetstockapi.dto.response.dashboard;

import com.stock.sweet.sweetstockapi.dto.response.ChartResponse;
import com.stock.sweet.sweetstockapi.dto.response.IngredientDashboardResponse;
import com.stock.sweet.sweetstockapi.dto.response.NearExpireIngredients;
import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data
@Builder
public class DashboardResponse {
    private CardResponse cardResponse;
    private ChartResponse chartsResponse;
    private NearExpireIngredients nearExpireIngredients;
    private List<IngredientDashboardResponse> nearEndIngredients;
}
