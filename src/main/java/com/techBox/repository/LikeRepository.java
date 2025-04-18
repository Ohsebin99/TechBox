package com.techBox.repository;

import com.techBox.entity.LikeEntity;
import com.techBox.entity.MemberEntity;
import com.techBox.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeRepository extends JpaRepository<LikeEntity, Long> {


    void deleteByProductIdAndIdIndex(ProductEntity productId, MemberEntity idIndex);

    boolean existsByProductIdAndIdIndex(ProductEntity productEntity, MemberEntity memberEntity);

    Long countByIdIndex(MemberEntity memberEntity);

    List<LikeEntity> findByIdIndex(MemberEntity idIndex);
}
