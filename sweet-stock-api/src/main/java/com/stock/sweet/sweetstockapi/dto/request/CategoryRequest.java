package com.stock.sweet.sweetstockapi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryRequest {
    private String name;
    private Double storageTemperature;
    private Boolean isRefrigerated;
    private String unitMeasurement;
    private LocalDate dateInsert;
    private LocalDate dateUpdate;
}
