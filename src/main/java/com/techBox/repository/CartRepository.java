package com.techBox.repository;

import com.techBox.entity.CartEntity;
import com.techBox.entity.MemberEntity;
import com.techBox.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<CartEntity, Long> {
    Optional<CartEntity> findByProductIdAndIdIndex(ProductEntity productId, MemberEntity idIndex);
}
