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
public class LikeController {

    private final LikeService likeService;

//    @DeleteMapping("/delete/like")
//    public String likeDisable(HttpSession session, @RequestParam("productId") ProductEntity productId,
//                              @RequestParam("idIndex") MemberEntity idIndex) {
//        System.out.println("삭제 부분: " + productId + ", " + idIndex);
//
//        likeService.deleteLike(productId, idIndex);
//        return "success";
//    }
//
//    @PostMapping("/insert/like")
//    public String likeActivate(@RequestParam("productId") ProductEntity productId,
//                               @RequestParam("idIndex") MemberEntity idIndex) {
//        System.out.println("삽입 부분: " + productId + ", " + idIndex);
//        likeService.save(productId, idIndex);
//        return "success";
//    }
    @DeleteMapping("/delete/like")
    @ResponseBody
    public String likeDisable(@RequestBody LikeDTO likeDTO) {
        System.out.println("삭제 부분: " + likeDTO.getProductId() + ", " + likeDTO.getIdIndex());

        likeService.deleteLike(likeDTO);
        return "success";
    }

        @PostMapping("/insert/like")
        @ResponseBody
        public String likeActivate(@RequestBody LikeDTO likeDTO) {
            System.out.println("삽입 부분: " + likeDTO.getProductId() + ", " + likeDTO.getIdIndex());
            likeService.save(likeDTO);
            return "success";
        }

}
