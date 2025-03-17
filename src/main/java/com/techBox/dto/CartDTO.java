package com.techBox.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CartDTO {
    private Long cartId;

    private Long productId;
    private Long idIndex;
}
