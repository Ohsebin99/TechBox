package com.techBox.controller;

import com.techBox.dto.MemberDTO;
import com.techBox.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class MemberController {
    // 생성자 주입
    private final MemberService memberService;

    // 회원가입 페이지 출력 요청
    @GetMapping("/member/signUp")
    public String memberSignUp() {

        return "member/signUp";
    }

    @PostMapping("/member/signUp")
    public String SignUpForm(@ModelAttribute MemberDTO memberDTO) {
        System.out.println(memberDTO);
        memberService.save(memberDTO);
        return "redirect:/";
    }

    // 로그인 페이지 출력 요청
    @GetMapping("/member/login")
    public String memberLogin() {

        return "member/login";
    }
}
