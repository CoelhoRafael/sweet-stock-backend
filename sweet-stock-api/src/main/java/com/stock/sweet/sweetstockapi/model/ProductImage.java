package com.stock.sweet.sweetstockapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "product_image")
public class ProductImage {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String name;
    private String type;
    @Lob
    private byte[] data;

    public ProductImage(String name, String type, byte[] data) {
        this.name = name;
        this.type = type;
        this.data = data;
    }

    //    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "fk_product", referencedColumnName = "id")
//    private Product product;
}
