package com.stock.sweet.sweetstockapi.mapper;

import com.stock.sweet.sweetstockapi.dto.response.dashboard.CardResponse;
import com.stock.sweet.sweetstockapi.dto.response.dashboard.DashboardResponse;
import com.stock.sweet.sweetstockapi.dto.response.dashboard.chart.ChartMonthItem;
import com.stock.sweet.sweetstockapi.dto.response.dashboard.chart.IngredientDashboardResponse;
import com.stock.sweet.sweetstockapi.dto.response.dashboard.chart.NearExpireIngredients;
import com.stock.sweet.sweetstockapi.model.Ingredient;
import com.stock.sweet.sweetstockapi.model.OutStock;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class DashboardMapper {

    public DashboardResponse convertToDashboardResponse(
            List<Ingredient> nearEndIngredients,
            List<OutStock> productsSoldMonth,
            List<Ingredient> expiredIngredients,
            List<Ingredient> monthExpenses,
            List<Ingredient> allIngredients,
            List<OutStock> allOutStock
    ) {


        return DashboardResponse
                .builder()
                .cards(
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
                .chart(generateChart(allIngredients, allOutStock))
                .nearExpireIngredients(
                        List.of(
                                NearExpireIngredients.builder()
                                        .date(LocalDate.now())
                                        .items(
                                                List.of(
                                                        IngredientDashboardResponse.builder().build()
                                                )
                                        )
                                        .build()
                        )
                )
                .nearEndIngredients(
                        List.of(
                                IngredientDashboardResponse.builder().build()
                        )
                )
                .build();
    }


    public List<ChartMonthItem> generateChart(List<Ingredient> x, List<OutStock> outStockList) {
        var mapOfIngredientsMonth = x.stream().map(ingredient -> {
                    ingredient.setDateInsert(ingredient.getDateInsert().withDayOfMonth(1));
                    return ingredient;
                }
        ).collect(Collectors.groupingBy(Ingredient::getDateInsert));

        var mapOfIngredientsMonthSum = mapOfIngredientsMonth
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, mapIngredient -> mapIngredient.getValue().stream()
                        .mapToDouble(i -> i.getBuyValue().doubleValue()).sum()
                ));

        List<ChartMonthItem> chartMonthList = new ArrayList<>();

        mapOfIngredientsMonthSum.forEach((localDate, sum) -> chartMonthList.add(
                ChartMonthItem
                        .builder()
                        .month(localDate)
                        .profit(0.0)
                        .spent(sum)
                        .build())
        );

        var mapOfOutStockMonth = outStockList.stream().map(outStock -> {
                    outStock.setDate(outStock.getDate().withDayOfMonth(1));
                    return outStock;
                }
        ).collect(Collectors.groupingBy(OutStock::getDate));

        var mapOfOutStockMonthSum = mapOfOutStockMonth
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, mapOutStock -> mapOutStock.getValue()
                                .stream().mapToDouble(os -> os.getProduct().getSaleValue().doubleValue()).sum()
                        )
                );

        mapOfOutStockMonthSum.forEach((localDate, aDouble) -> {
            chartMonthList.stream().filter(item -> item.getMonth() == localDate).map(chartMonthItem -> {
                chartMonthItem.setProfit(aDouble);
                return chartMonthItem;
            });
        });

        return chartMonthList;
    }
}
