package com.stock.sweet.sweetstockapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
@Table(name = "tb_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "uuid")
    private String uuid;

    @Column(name = "name")
    private String name;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "telephone_number")
    private String telephoneNumber;

    @Column(name = "level_access")
    private String levelAccess;

    @ManyToOne
    @JoinColumn(name = "fk_company", referencedColumnName = "id")
    private Company company;

    @Column(name = "aproved")
    private boolean aproved;

    @Column(name = "activated")
    private boolean activated;

    @Column(name = "profile_picture" ,length = 50 * 10240 * 10240)
    private String picture;

}