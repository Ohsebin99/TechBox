package com.techBox.service;

import com.techBox.entity.ProductEntity;
import com.techBox.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Page<ProductEntity> paging(Pageable pageable) {
        int page = pageable.getPageNumber() - 1;
        int pageLimit = 8;

     return productRepository.findAll(PageRequest.of(page, pageLimit));
    }
}