package com.stock.sweet.sweetstockapi.model;

import com.stock.sweet.sweetstockapi.controller.enums.UnitMeasurement;
import com.stock.sweet.sweetstockapi.model.enums.CategoryEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "tb_product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "uuid")
    private String uuid;

    @Column(name = "name")
    private String name;

    @Column(name = "unit_measurement")
    private UnitMeasurement unitMeasurement;

    @Column(name = "sale_value")
    private BigDecimal saleValue;

    @Column(name = "total")
    private Double total;

    @Column(name = "expiration_date")
    private Date expirationDate;

    @Column(name = "date_insert")
    private LocalDate dateInsert;

    @Column(name = "date_update")
    private LocalDate dateUpdate;

    @Column(name = "category")
    private CategoryEnum category;

    @Column(name = "is_refigerated")
    private Boolean isRefigerated;

    @Column(name = "sold")
    private Boolean sold;

    @Column(name = "picture")
    private String picture;

    @OneToMany(mappedBy = "product")
    List<Confection> confections;

}
