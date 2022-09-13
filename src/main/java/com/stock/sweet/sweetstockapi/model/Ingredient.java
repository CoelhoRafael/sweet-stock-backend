package com.stock.sweet.sweetstockapi.model;

import com.stock.sweet.sweetstockapi.controller.enums.UnitMeasurement;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Getter
@Setter
@Table(name = "tb_ingredient")
public class Ingredient {

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

    @Column(name = "number_units")
    private Integer numberUnits;

    @Column(name = "quantity_per_unit")
    private Double quantityPerUnit;

    @Column(name = "expiration_date")
    private LocalDate expirationDate;

    @Column(name = "storage_temperature")
    private Double storageTemperature;

    @Column(name = "is_refigerated")
    private Boolean isRefigerated;

    @Column(name = "buy_value")
    private Double buyValue;

    @Column(name = "provide_code")
    private Integer provideCode;

    @Column(name = "quantity_used")
    private Integer quantityUsed;

    @Column(name = "date_insert")
    private LocalDate dateInsert;

    @Column(name = "date_update")
    private LocalDate dateUpdate;

    @Column(name = "number_lot")
    private Integer numberLot;

    @Column(name = "expirated")
    private Boolean expirated;

    @Column(name = "view_in_reports")
    private Boolean viewInReports;

    @Column(name = "uuid_company")
    private String uuidCompany;

    @Column(name = "total")
    private Double total;

    @Column(name = "picture", columnDefinition = "text")
    private String picture;

    @Column(name = "brand")
    private String brand;

    @Column(name = "purchase_date")
    private LocalDate purchaseDate;

    @OneToMany(mappedBy = "ingredient")
    Set<Confection> confections;

}
