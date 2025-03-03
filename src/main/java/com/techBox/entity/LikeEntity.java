package com.techBox.entity;

import com.techBox.dto.LikeDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.apache.catalina.User;

@Entity
@Setter
@Getter
@Table(name = "like_table", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id", "product_id"})
})
public class LikeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likeId;

    @ManyToOne
    @JoinColumn(name = "idIndex", nullable = false)
    private MemberEntity idIndex;

    // 여기서 product 엔티티 반환
    @Getter
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity productId;

}
