package com.techBox.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@ToString
@Table(name = "product_table")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(length = 30)
    private String productName;

    @Column(length = 15)
    private int price;

    @Column
    private String productImage;

    @Column
    private Boolean liked;

    @Column(length = 30)
    private int quantity;
}
