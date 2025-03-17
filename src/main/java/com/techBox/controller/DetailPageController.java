package com.techBox.controller;

import com.techBox.dto.CartDTO;
import com.techBox.entity.ProductEntity;
import com.techBox.service.CartService;
import com.techBox.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class DetailPageController {

    private final ProductService productService;
    private final CartService cartService;


    @GetMapping("/detailPage/{id}")
    public String detailPage(@PathVariable("id") Long productId, Model model){
        ProductEntity detailProduct = productService.selectDetailProduct(productId);
        model.addAttribute("detailProduct", detailProduct);
    return "product/detail";
    }

    @ResponseBody
    @PostMapping("/insert/cart")
    public String cartInsert(@RequestBody CartDTO cartDTO){
        cartService.save(cartDTO);

        return "success";
    }

}
