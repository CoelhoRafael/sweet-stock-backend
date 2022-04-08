package com.stock.sweet.sweetstockapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "ingredient")
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "uuid")
    private String uuid;

    @Column(name = "name")
    private String name;

    @Column(name = "unitMeasurement")
    private String unitMeasurement;

    @Column(name = "quantity")
    private BigDecimal quantity;

    @Column(name = "expiration_date")
    private LocalDate expirationDate;

    @Column(name = "storage_temperature")
    private Double storageTemperature;

    @Column(name = "is_refigerated")
    private Boolean isRefigerated;

    @Column(name = "buy_value")
    private BigDecimal buyValue;

    @Column(name = "provide_code")
    private Integer provideCode;

    @Column(name = "quantity_used")
    private BigDecimal quantityUsed;

    @Column(name = "date_insert")
    private LocalDate dateInsert;

    @Column(name = "date_update")
    private LocalDate dateUpdate;

    @Column(name = "number_lot")
    private Integer numberLot;

    @OneToMany(mappedBy = "ingredient")
    Set<Confection> confections;


}
