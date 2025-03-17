package com.techBox.repository;

import com.techBox.entity.LikeEntity;
import com.techBox.entity.MemberEntity;
import com.techBox.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {


    ProductEntity findByProductId(ProductEntity productEntity);
}
