package com.techBox.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ProductDTO {

    private Long productId;
    private String productName;
    private int price;
    private String productImage;
    private boolean liked;
    private int quantity;
}
