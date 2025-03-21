package com.techBox.controller;

import com.techBox.dto.CartDTO;
import com.techBox.entity.ProductEntity;
import com.techBox.service.CartService;
import com.techBox.service.ProductService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class DetailPageController {

    private final ProductService productService;

    // 상세페이지
    @GetMapping("/detailPage/{id}")
    public String detailPage(@PathVariable("id") Long productId, Model model){
        ProductEntity detailProduct = productService.selectDetailProduct(productId);
        model.addAttribute("detailProduct", detailProduct);
    return "product/detail";
    }

}
