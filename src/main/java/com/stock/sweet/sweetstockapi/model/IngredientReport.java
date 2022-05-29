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
@Table(name = "tb_ingredient_report")
public class IngredientReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "uuid")
    private String uuid;

    @Column(name = "date_insert")
    private LocalDateTime dateInsert;

    @Column(name = "quantity_days_for_generate_report")
    private Integer quantityDaysForGenerateReport;

    @Column(name = "arquivo_txt")
    private String arquivoTxt;

    @Column(name = "id_company")
    private Integer idCompany;

}

