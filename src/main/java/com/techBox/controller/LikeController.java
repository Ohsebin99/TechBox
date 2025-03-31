package com.techBox.controller;

import com.techBox.dto.LikeDTO;
import com.techBox.entity.ProductEntity;
import com.techBox.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    // 좋아요 유지
    @ResponseBody
    @GetMapping("/check/like")
    public boolean checkLike(@RequestParam Long productId, @RequestParam Long idIndex) {
        return likeService.isLiked(productId, idIndex);
    }


    // 좋아요 취소
    @ResponseBody
    @DeleteMapping("/delete/like")
    public String likeDisable(@RequestBody LikeDTO likeDTO) {
        likeService.deleteLike(likeDTO);
        return "success";
    }

    // 좋아요 활성화
    @ResponseBody
    @PostMapping("/insert/like")
    public String likeActivate(@RequestBody LikeDTO likeDTO) {
        likeService.save(likeDTO);
        return "success";
    }

    // 좋아요 카운트
    @ResponseBody
    @GetMapping("/count/like")
    public Long countLike(@RequestParam Long idIndex) {
        return likeService.count(idIndex);
    }

    // 좋아요 페이지
    @GetMapping("/likeList")
    public String likeList(Model model,
                           @SessionAttribute(name = "idIndex", required = false) Long idIndex,
                           RedirectAttributes rab){
        if(idIndex == null){
            rab.addFlashAttribute("loginError", "로그인이 필요합니다.");
            return "redirect:/member/login";
        }

        List<ProductEntity> likedProducts = likeService.getLikedProducts(idIndex);
        model.addAttribute("likedProducts", likedProducts);
        return "/product/likeList"; // likeList.html 페이지 반환
    }
}
