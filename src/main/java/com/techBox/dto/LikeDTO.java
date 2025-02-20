package com.techBox.dto;

import com.techBox.entity.MemberEntity;
import com.techBox.entity.ProductEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LikeDTO {
    private Long likeId;

    private Long productId;
    private Long idIndex;
}
