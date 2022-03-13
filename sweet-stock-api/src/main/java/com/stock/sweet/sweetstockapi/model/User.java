package com.stock.sweet.sweetstockapi.model;

import com.stock.sweet.sweetstockapi.model.enums.LevelAccess;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "user")
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "uuid")
    private UUID uuid;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "telephone_number")
    private String telephoneNumber;

    @Column(name = "level_access")
    private LevelAccess levelAccess;

    public abstract String login(String email, String password);
}