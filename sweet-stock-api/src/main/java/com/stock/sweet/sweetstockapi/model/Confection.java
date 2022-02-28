package com.stock.sweet.sweetstockapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "confection")
public class Confection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "uuid")
    private String uuid;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "quantity_ingredient")
    private BigDecimal quantityIngredient;

    @Column(name = "cost")
    private BigDecimal cost;

    @Column(name = "employee")
    private Integer employee;


}
