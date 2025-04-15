package com.techBox.entity;

import com.techBox.dto.CartDTO;
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

    public static CartEntity toCartEntity(CartDTO cartDTO) {
        CartEntity cartEntity = new CartEntity();
        cartEntity.setProductName(cartDTO.getProductName());
        cartEntity.setPrice(cartDTO.getPrice());
        cartEntity.setProductImage(cartDTO.getProductImage());
        cartEntity.setQuantity(cartDTO.getQuantity());

        return cartEntity;

    }
}
