package com.stock.sweet.sweetstockapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "tb_confection")
@Builder
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
    private Integer quantity;

    @Column(name = "cost")
    private Double cost;

    @ManyToOne
    @JoinColumn(name = "id_product", referencedColumnName = "id")
    Product product;

    @ManyToOne
    @JoinColumn(name = "id_ingredient", referencedColumnName = "id")
    Ingredient ingredient;
}
