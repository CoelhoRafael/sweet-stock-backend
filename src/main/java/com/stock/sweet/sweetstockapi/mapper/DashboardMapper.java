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
            List<Ingredient> nearExpireIngredients,
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
                                .nearEndIngredients(nearExpireIngredients.size())
                                .productsSoldMonth(productsSoldMonth.size())
                                .expiredIngredients(expiredIngredients.size())
                                .monthExpenses(monthExpenses
                                        .stream()
                                        .mapToDouble(i -> i.getBuyValue().doubleValue())
                                        .sum())
                                .build()
                )
                .chart(generateChart(allIngredients, allOutStock))
                .nearExpireIngredients(getNearExpiredIngredients(nearExpireIngredients))
                .nearEndIngredients(getNearEndIngredients(allIngredients))
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
                        .month(getMonthNameFromDate(localDate))
                        .profit(0.0)
                        .spent(sum)
                        .build())
        );

        var mapOfOutStockMonth = outStockList.stream().map(outStock -> {
                    outStock.setDate(outStock.getDate().withDayOfMonth(1));
                    return outStock;
                }
        ).collect(Collectors.groupingBy(a -> getMonthNameFromDate(a.getDate())));

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

    private String getMonthNameFromDate(LocalDate date) {
        String month;
        switch (date.getMonth().getValue()) {
            case 1:
                month = "Janeiro";
                break;

            case 2:
                month = "Fevereiro";
                break;

            case 3:
                month = "Março";
                break;

            case 4:
                month = "Abril";
                break;

            case 5:
                month = "Maio";
                break;

            case 6:
                month = "Junho";
                break;

            case 7:
                month = "Julho";
                break;

            case 8:
                month = "Agosto";
                break;

            case 9:
                month = "Setembro";
                break;

            case 10:
                month = "Outubro";
                break;

            case 11:
                month = "Novembro";
                break;

            case 12:
                month = "Dezembro";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + date.getMonth().getValue());
        }
        return month;
    }


    public List<NearExpireIngredients> getNearExpiredIngredients(List<Ingredient> ingredients) {
        List<NearExpireIngredients> listNearExpired = new ArrayList<>();

        var a = ingredients
                .stream()
                .map(i -> IngredientDashboardResponse
                        .builder()
                        .uuid(i.getUuid())
                        .amount(i.getQuantity().doubleValue())
                        .name(i.getName())
                        .build()
                ).collect(Collectors.toList());

        var batatinha =
                ingredients.stream().map(
                        i -> {
                            i.getDateInsert().withDayOfMonth(1);
                            return i;
                        }
                ).collect(Collectors.groupingBy(Ingredient::getExpirationDate));

        batatinha.forEach((date, ingredients1) -> {
            listNearExpired.add(NearExpireIngredients
                    .builder()
                    .date(String.format("%d de %s", date.getDayOfMonth(), getMonthNameFromDate(date)))
                    .items(
                            ingredients1.
                                    stream()
                                    .map(i -> IngredientDashboardResponse
                                            .builder()
                                            .uuid(i.getUuid())
                                            .amount(i.getQuantity().doubleValue())
                                            .name(i.getName())
                                            .build()
                                    )
                                    .collect(Collectors.toList())
                    )
                    .build());
        });

        return listNearExpired;
    }

    public List<IngredientDashboardResponse> getNearEndIngredients(List<Ingredient> ingredients) {
        return ingredients
                .stream()
                .filter(ingredient -> ingredient.getQuantityUsed().doubleValue()
                        >= (.75 * ingredient.getQuantity().doubleValue()))
                .map(i -> IngredientDashboardResponse
                        .builder()
                        .uuid(i.getUuid())
                        .amount(i.getQuantity().doubleValue())
                        .name(i.getName())
                        .build())
                .collect(Collectors.toList());
    }
}
