package com.stock.sweet.sweetstockapi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryResponse {
    private String uuid;
    private String name;
    private Double storageTemperature;
    private Boolean isRefrigerated;
    private String unitMeasurement;
    private LocalDateTime dateInsert;
    private LocalDateTime dateUpdate;
}
