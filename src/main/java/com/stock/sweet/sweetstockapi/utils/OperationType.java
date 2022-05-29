package com.stock.sweet.sweetstockapi.utils;

public enum OperationType {
    ADD_INGREDIENT,
    REMOVE_INGREDIENT;

    OperationType() {
    }

    public OperationType getUndoOperation() {
        switch (this) {
            case ADD_INGREDIENT:
                return REMOVE_INGREDIENT;
            case REMOVE_INGREDIENT:
                return ADD_INGREDIENT;
            default:
                return null;
        }
    }
}
