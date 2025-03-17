package com.techBox.service;

import com.techBox.entity.ProductEntity;
import com.techBox.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Page<ProductEntity> paging(Pageable pageable) {
        int page = pageable.getPageNumber() - 1;
        int productLimit = 8; // 한 페이지에 보여줄 게시물 개수

     return productRepository.findAll(PageRequest.of(page, productLimit));
    }

    public ProductEntity selectDetailProduct(Long productId) {
        Optional<ProductEntity> byProduct = productRepository.findById(productId);
        return byProduct.get();

    }
}

