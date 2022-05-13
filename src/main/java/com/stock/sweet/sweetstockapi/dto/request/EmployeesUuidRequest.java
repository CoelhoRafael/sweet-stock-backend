package com.stock.sweet.sweetstockapi.dto.request;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class EmployeesUuidRequest {
    private List<String> uuids;

    public EmployeesUuidRequest() {
    }

    public EmployeesUuidRequest(List<String> uuids) {
        this.uuids = uuids;
    }
}
