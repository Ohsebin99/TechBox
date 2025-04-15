package com.techBox.controller;

import com.techBox.dto.CartDTO;
import com.techBox.entity.ProductEntity;
import com.techBox.service.CartService;
import com.techBox.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    // 장바구니 추가
    @ResponseBody
    @PostMapping("/insert/cart")
    public String cartInsert(@RequestBody CartDTO cartDTO){
        System.out.println("카트카트" + cartDTO);
        return cartService.save(cartDTO);
    }

    // 장바구니 페이지
    @GetMapping("/cartList")
    public String cartList(Model model,
                           @SessionAttribute(name = "idIndex", required = false) Long idIndex,
                           RedirectAttributes rab){
        if(idIndex == null){
            rab.addFlashAttribute("loginError", "로그인이 필요합니다.");
            return "redirect:/member/login";
        }

        List<ProductEntity> cartProducts = cartService.getLikedProducts(idIndex);
        model.addAttribute("cartProducts", cartProducts);
        return "/product/cartList";
    }

    // 장바구니 카운트
    @ResponseBody
    @GetMapping("/count/cart")
    public Long countCart(@RequestParam Long idIndex, Model model) {
        return cartService.count(idIndex);
    }

}
