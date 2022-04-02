package com.stock.sweet.sweetstockapi.model.enums;

public enum LevelAccess {
    ADMINISTRATOR(0, "ADMINISTRATOR"),
    EMPLOYEE(1, "EMPLOYEE"),
    EMPLOYEE_NOT_VERIFIED(2, "EMPLOYEE_NOT_VERIFIED"),
    CUSTOMER(3, "CUSTOMER");

    LevelAccess(int id, String name) {
    }
}