package com.stock.sweet.sweetstockapi.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Operation {
    private String alteredItemUuid;
    private OperationType operationType;
}
