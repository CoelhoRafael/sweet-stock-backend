package com.stock.sweet.sweetstockapi.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "tb_category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "uuid")
    private String uuid;

    @Column(name = "name")
    private String name;

    @Column(name = "storage_temperature")
    private Double storageTemperature;

    @Column(name = "is_refrigerated")
    private Boolean isRefrigerated;

    @Column(name = "unit_measurement")
    private String unitMeasurement;

    @Column(name = "date_insert")
    private LocalDateTime dateInsert;

    @Column(name = "date_update")
    private LocalDateTime dateUpdate;
}
