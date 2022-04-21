package com.stock.sweet.sweetstockapi.dto.response.dashboard;

import com.stock.sweet.sweetstockapi.dto.response.dashboard.chart.ChartMonthItem;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ChartResponse {
    private List<ChartMonthItem> chartMonthItem;
}
