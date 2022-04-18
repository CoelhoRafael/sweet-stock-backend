package com.stock.sweet.sweetstockapi.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChartResponse {
    private Double january;
    private Double february;
    private Double march;
    private Double april;
    private Double may;
    private Double june;
    private Double july;
    private Double august;
    private Double september;
    private Double october;
    private Double november;
    private Double december;
}
