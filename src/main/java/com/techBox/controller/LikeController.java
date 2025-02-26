package com.techBox.controller;

import com.techBox.dto.LikeDTO;
import com.techBox.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@ResponseBody
public class LikeController {

    private final LikeService likeService;

    // 좋아요 유지
    @GetMapping("/check/like")
    public boolean checkLike(@RequestParam Long productId, @RequestParam Long idIndex) {
        return likeService.isLiked(productId, idIndex);
    }


    // 좋아요 취소
    @DeleteMapping("/delete/like")
    public String likeDisable(@RequestBody LikeDTO likeDTO) {
        likeService.deleteLike(likeDTO);
        return "success";
    }

    // 좋아요 활성화
    @PostMapping("/insert/like")
    public String likeActivate(@RequestBody LikeDTO likeDTO) {
        likeService.save(likeDTO);
        return "success";
    }

    // 찜 카운트
    @GetMapping("/count/like")
    public Long countLike(@RequestParam Long idIndex) {
        return likeService.count(idIndex);
    }
}
