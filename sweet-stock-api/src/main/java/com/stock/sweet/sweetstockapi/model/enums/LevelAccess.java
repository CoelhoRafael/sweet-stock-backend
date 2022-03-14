package com.stock.sweet.sweetstockapi.model.enums;

public enum LevelAccess {
    ADMINISTRATOR(0, "Administrator"),
    EMPLOYEE(1, "Funcionário"),
    CUSTOMER(2, "Cliente");
    LevelAccess(int id, String name) {
    }
}