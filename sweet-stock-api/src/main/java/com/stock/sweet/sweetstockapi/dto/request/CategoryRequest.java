package com.stock.sweet.sweetstockapi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryRequest {
    private String name;
    private Double storageTemperature;
    private Boolean isRefrigerated;
    private String unitMeasurement;
    private LocalDateTime dateInsert;
    private LocalDateTime dateUpdate;
}
