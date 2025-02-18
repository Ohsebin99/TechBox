package com.techBox.controller;

import com.techBox.dto.MemberDTO;
import com.techBox.entity.MemberEntity;
import com.techBox.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    // 회원가입
    @PostMapping("/member/signUp")
    public String SignUpForm(@ModelAttribute MemberDTO memberDTO, RedirectAttributes rab) {
        System.out.println(memberDTO);
        memberService.save(memberDTO);

        rab.addFlashAttribute("signUpSuccess", memberDTO.getUserId() + "님 가입을 축하합니다.");
        return "member/login";
    }

    // 로그인 페이지 출력 요청
    @GetMapping("/member/login")
    public String loginForm() {

        return "member/login";
    }

    // 로그인
    @PostMapping("/member/login")
    public String login(@ModelAttribute MemberDTO memberDTO, HttpSession session, RedirectAttributes rab) {
        MemberDTO loginResult = memberService.login(memberDTO);
        if (loginResult != null) {
            // 로그인 성공
            // session
            session.setAttribute("userId", loginResult.getUserId());
            session.setAttribute("nickname", loginResult.getNickname());
            rab.addFlashAttribute("loginSuccess", loginResult.getNickname() + "님 환영합니다.");
            return "redirect:/";
        } else {
            // 로그인 실패
            rab.addFlashAttribute("loginError", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "redirect:/member/login";
        }
    }

    // 로그아웃
    @GetMapping("/member/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    // 아이디 중복 체크
    @PostMapping("/member/id-check")
    public @ResponseBody String idCheck(@RequestParam("userId") String userId) {
        String checkResult = memberService.idCheck(userId);
        if (checkResult != null) {
            // 아이디 사용 가능
            return "success";
        } else {
            // 아이디 사용 불가능
            return "fail";
        }
    }

    // 닉네임 중복 체크
    @PostMapping("/member/nickname-check")
    public @ResponseBody String nicknameCheck(@RequestParam("nickname") String nickname) {
        String checkResult = memberService.nicknameCheck(nickname);

        if (checkResult != null) {
            // 아이디 사용 가능
            return "success";
        } else {
            // 아이디 사용 불가능
            return "fail";
        }
    }


}
