package com.techBox.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "product_table")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(unique = true, length = 30)
    private String productName;

    @Column(length = 15)
    private int price;

    @Column
    private String productImage;

    @Column
    private Boolean liked;
}
