package com.stock.sweet.sweetstockapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "tb_out_stock")
public class OutStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "uuid")
    private String uuid;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "is_expired_product")
    private Boolean isExpiredProduct;

    @Column(name = "id_company")
    private String idCompany;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_product", referencedColumnName = "id")
    private Product product;


}
