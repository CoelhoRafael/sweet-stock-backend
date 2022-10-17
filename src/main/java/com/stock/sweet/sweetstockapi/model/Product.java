package com.stock.sweet.sweetstockapi.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Locale;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Getter
@Setter
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
    private String unitMeasurement;

    @Column(name = "sale_value")
    private BigDecimal saleValue;

    @Column(name = "total")
    private Integer total;

    @Column(name = "expiration_date")
    private Date expirationDate;

    @Column(name = "date_insert")
    private LocalDate dateInsert;

    @Column(name = "date_update")
    private LocalDate dateUpdate;

    @Column(name = "is_refigerated")
    private Boolean isRefigerated;

    @Column(name = "sold")
    private Boolean sold;

    @Column(name = "date_of_sale")
    private LocalDateTime dateOfSale;

    @Column(name = "picture")
    private String picture;

    @OneToMany(mappedBy = "product")
    Set<Confection> confections;

    @JoinColumn(name = "fk_company")
    @ManyToOne
    private Company company;

    @JoinColumn(name = "fk_category")
    @ManyToOne
    private Category category;

}
