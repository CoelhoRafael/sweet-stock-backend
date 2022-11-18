package com.stock.sweet.sweetstockapi.model;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Getter
@Setter
@Table(name = "tb_nutritional_facts")
public class NutritionalFacts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "calories")
    private Double calories;

    @Column(name = "sodium")
    private Double sodium;

    @Column(name = "sugars")
    private Double sugars;

    @Column(name = "protein")
    private Double protein;

    @Column(name = "fat")
    private Double fat;

    @Column(name = "weight")
    private Double weight;

    @Column(name = "gluten")
    private Double gluten;
}
