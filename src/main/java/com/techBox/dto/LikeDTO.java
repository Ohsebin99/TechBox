package com.techBox.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LikeDTO {
    private Long likeId;

    private Long productId;
    private Long userId;
}
