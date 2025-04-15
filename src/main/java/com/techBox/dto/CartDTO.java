package com.techBox.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CartDTO {
    private Long cartId;

    private Long productId;
    private Long idIndex;
    private String productName;
    private int price;
    private String productImage;
    private boolean liked;
    private int quantity;
}
