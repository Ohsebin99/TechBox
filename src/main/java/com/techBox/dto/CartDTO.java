package com.techBox.dto;

import com.techBox.entity.CartEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

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
    private int sumPrice;
    private String productImage;
    private boolean liked;
    private int quantity;

}
