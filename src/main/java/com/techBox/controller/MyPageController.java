package com.techBox.controller;

import com.techBox.dto.MemberDTO;
import com.techBox.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MyPageController {

    private final MemberService memberService;

    @GetMapping("/myPage")
    public String myPage(@RequestParam("idIndex") Long idIndex, Model model, RedirectAttributes rab){
        System.out.println("인덱스 : " + idIndex);
        if (idIndex == 0) {
            rab.addFlashAttribute("loginError", "로그인이 필요합니다.");
            return "redirect:/member/login";
        }else {
            MemberDTO user = memberService.selectMember(idIndex);
            model.addAttribute("nickname", user.getNickname());
            return "/myPage/myPage";

        }
    }

    @GetMapping("/userUpdatePage")
    public String userUpdatePage(@RequestParam("idIndex") Long idIndex, Model model, RedirectAttributes rab){

        MemberDTO user = memberService.selectMember(idIndex);
        model.addAttribute("user", user);

        return "/myPage/userUpdate";
    }
}
