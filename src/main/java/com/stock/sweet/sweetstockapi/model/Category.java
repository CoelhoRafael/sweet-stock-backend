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
@Table(name = "tb_category")
public class Category {

    @Id
    @Column(name = "id")
    private String  id;

    private String name;

    @Column(name = "profile_picture", columnDefinition = "text")
    private String picture;
}
