package com.techBox.controller;

import com.techBox.dto.LikeDTO;
import com.techBox.entity.MemberEntity;
import com.techBox.entity.ProductEntity;
import com.techBox.service.LikeService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@ResponseBody
public class LikeController {

    private final LikeService likeService;

    // 좋아요 확인
    @GetMapping("/check/like")
    public boolean checkLike(@RequestParam Long productId, @RequestParam Long idIndex) {
        return likeService.isLiked(productId, idIndex);
    }


    // 좋아요 취소
    @DeleteMapping("/delete/like")
    public String likeDisable(@RequestBody LikeDTO likeDTO) {
        System.out.println("삭제 부분: " + likeDTO.getProductId() + ", " + likeDTO.getIdIndex());

        likeService.deleteLike(likeDTO);
        return "success";
    }

    // 좋아요
    @PostMapping("/insert/like")
    public String likeActivate(@RequestBody LikeDTO likeDTO) {
        System.out.println("삽입 부분: " + likeDTO.getProductId() + ", " + likeDTO.getIdIndex());
        likeService.save(likeDTO);
        return "success";
    }

}
