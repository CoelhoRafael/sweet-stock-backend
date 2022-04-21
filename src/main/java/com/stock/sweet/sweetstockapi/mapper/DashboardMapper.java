package com.stock.sweet.sweetstockapi.mapper;

import com.stock.sweet.sweetstockapi.dto.response.dashboard.CardResponse;
import com.stock.sweet.sweetstockapi.dto.response.ChartResponse;
import com.stock.sweet.sweetstockapi.dto.response.dashboard.DashboardResponse;
import com.stock.sweet.sweetstockapi.model.Ingredient;
import com.stock.sweet.sweetstockapi.model.OutStock;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DashboardMapper {

    public DashboardResponse convertToDashboardResponse(
            List<Ingredient> nearEndIngredients,
            List<OutStock> productsSoldMonth,
            List<Ingredient> expiredIngredients,
            List<Ingredient> monthExpenses,
            List<Ingredient> allIngredients) {


        return DashboardResponse
                .builder()
                .cardResponse(
                        CardResponse.builder()
                                .nearEndIngredients(nearEndIngredients.size())
                                .productsSoldMonth(productsSoldMonth.size())
                                .expiredIngredients(expiredIngredients.size())
                                .monthExpenses(monthExpenses
                                        .stream()
                                        .mapToDouble(i -> i.getBuyValue().doubleValue())
                                        .sum())
                                .build()
                )
                .chartsResponse(
                        ChartResponse
                                .builder()
                                .build()
                )
                .build();
    }
}
