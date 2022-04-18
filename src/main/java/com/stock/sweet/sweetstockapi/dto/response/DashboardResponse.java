package com.stock.sweet.sweetstockapi.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data
@Builder
public class DashboardResponse {
    private CardsResponse cardsResponse;
    private ChartResponse chartsResponse;
    private NearExpireIngredients nearExpireIngredients;
    private List<IngredientDashboardResponse> nearEndIngredients;
}
