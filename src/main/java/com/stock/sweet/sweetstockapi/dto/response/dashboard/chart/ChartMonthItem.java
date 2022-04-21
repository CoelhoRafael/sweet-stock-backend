package com.stock.sweet.sweetstockapi.dto.response.dashboard.chart;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class ChartMonthItem {
    private LocalDate month;
    private Double profit;
    private Double spent;
}
