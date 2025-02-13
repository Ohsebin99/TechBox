package com.techBox.controller;

import com.techBox.dto.ProductDTO;
import com.techBox.entity.ProductEntity;
import com.techBox.repository.ProductRepository;
import com.techBox.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final ProductService productService;

    @GetMapping("/")
    public String home(@PageableDefault(page = 1, size = 8, sort = "id", direction = Sort.Direction.DESC)
                           Pageable pageable, Model model) {

        Page<ProductEntity> productPage = productService.paging(pageable);

        int blockLimit = 10;
        int startPage = (((int)(Math.ceil((double)pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1;
        int endPage = Math.min((startPage + blockLimit - 1), productPage.getTotalPages());

        model.addAttribute("productList", productPage.getContent());  // 페이지별 제품 리스트
        model.addAttribute("currentPage", pageable.getPageNumber());  // 현재 페이지 번호
        model.addAttribute("startPage", startPage);  // 시작 페이지
        model.addAttribute("endPage", endPage);  // 끝 페이지


        return "main";
    }

}