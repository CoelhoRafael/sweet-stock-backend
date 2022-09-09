package com.stock.sweet.sweetstockapi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryResponse {
    private String id;
    private String name;
}
