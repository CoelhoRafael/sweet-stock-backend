package com.stock.sweet.sweetstockapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "tb_company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "uuid")
    private String uuid;

    @Column(name = "name")
    private String name;

    @Column(name = "fantansy_name")
    private String fantansyName;

    @Column(name = "ceo")
    private String ceo;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "cnpj")
    private String cnpj;

    @Column(name = "email")
    private String email;

    @Column(name = "telephone_number")
    private String telephoneNumber;

    @Column(name = "associate_code")
    private String associateCode;

    @Column(name = "profile_picture" ,length = 50 * 1024 * 1024)
    private String picture;

    @Column(name= "activated")
    private boolean activated;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_address", referencedColumnName = "id")
    private Address address;
}

