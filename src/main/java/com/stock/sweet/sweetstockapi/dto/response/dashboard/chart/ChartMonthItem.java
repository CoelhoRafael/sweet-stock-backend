package com.stock.sweet.sweetstockapi.dto.response.dashboard.chart;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChartMonthItem {
    private String month;
    private Double profit;
    private Double spent;
}
