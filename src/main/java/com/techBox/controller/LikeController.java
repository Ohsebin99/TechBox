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

    // 찜하기 유지
    @GetMapping("/check/like")
    public boolean checkLike(@RequestParam Long productId, @RequestParam Long idIndex) {
            return likeService.isLiked(productId, idIndex);
    }


    // 찜하기 취소
    @DeleteMapping("/delete/like")
    public String likeDisable(@RequestBody LikeDTO likeDTO) {

        likeService.deleteLike(likeDTO);
        return "success";
    }

    // 찜하기
    @PostMapping("/insert/like")
    public String likeActivate(@RequestBody LikeDTO likeDTO) {
        likeService.save(likeDTO);
        return "success";
    }

    @GetMapping("/count/like")
    public Long countLike(@RequestParam Long idIndex, Model model) {
        Long count = likeService.count(idIndex);
        model.addAttribute("countLike", count);
        System.out.println("라이크 카운트: " + count);
        return count;
    }
}
