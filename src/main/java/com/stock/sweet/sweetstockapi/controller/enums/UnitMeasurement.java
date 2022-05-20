package com.stock.sweet.sweetstockapi.controller.enums;

public enum UnitMeasurement {
    LITRO(1, "Litro"),
    MILILITRO(2, "Mililítro"),
    QUILO(3, "Quilo"),
    MILIGRAMA(4, "Miligrama"),
    UNIDADE(5, "Unidade"),
    GRAMA(6, "Grama"),
    ;

    public final String value;
    public final int codigo;

    UnitMeasurement(int codigo, String value) {
        this.value = value;
        this.codigo = codigo;
    }
}
