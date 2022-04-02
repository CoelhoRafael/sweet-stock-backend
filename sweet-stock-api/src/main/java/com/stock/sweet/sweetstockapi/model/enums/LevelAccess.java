package com.stock.sweet.sweetstockapi.model.enums;

public enum LevelAccess {
    ADMINISTRATOR(0, "Administrator"),
    EMPLOYEE(1, "Funcionário"),
    EMPLOYEE_NOT_VERIFIED(2, "Funcionário não verificado"),
    CUSTOMER(3, "Cliente");

    LevelAccess(int id, String name) {
    }
}