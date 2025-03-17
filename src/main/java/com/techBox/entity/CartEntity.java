package com.techBox.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "cart_table", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id", "product_id"})
})
public class CartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;

    @ManyToOne
    @JoinColumn(name = "idIndex", nullable = false)
    private MemberEntity idIndex;

    // 여기서 product 엔티티 반환
    @Getter
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity productId;
}
