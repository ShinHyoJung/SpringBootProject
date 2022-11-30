package com.project.shop.feature.member.controller;

import com.project.shop.feature.code.error.ErrorCode;
import com.project.shop.feature.code.success.SuccessCode;
import com.project.shop.feature.member.dto.*;
import com.project.shop.feature.member.entity.Member;
import com.project.shop.feature.member.service.MemberService;
import javafx.beans.property.IntegerProperty;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.encrypt.AesBytesEncryptor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private static final String VIEW_PREFIX = "member/";
    private final MemberService memberService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @GetMapping("/signUp") // 회원가입 페이지
    public String getSignUp(Model model) {
        model.addAttribute("main", VIEW_PREFIX + "signUp");
        return "view";
    }

    @PostMapping("/signUp") // 회원가입
    public String postSignUp(Model model, PostSignUp postSignUp) {
        String password = postSignUp.getPassword();
        String encryptPassword = bCryptPasswordEncoder.encode(password);
        memberService.insert(postSignUp.toEntity(encryptPassword));
        model.addAttribute("main", "main/default");
        return "view";
    }

    @GetMapping("/login")
    public String getLogin(Model model) {
        model.addAttribute("main", VIEW_PREFIX + "login");
        return "view";
    }
    @PostMapping("/login")
    public String postLogin(Model model, HttpSession session, PostLogin postLogin) {
        boolean isValidate = memberService.isValidateIDPWD(postLogin);

        if(isValidate) {
            Member member = memberService.select(postLogin.getId());
            int idx = member.getIdx();
            session.setAttribute("idx", idx);
            session.setAttribute("loggedIn", postLogin.getId());
        }
        model.addAttribute("main", "main/default");
        return "view";
    }

    @GetMapping("/logout")
    public String postLogout(Model model, HttpSession session) {
        session.removeAttribute("loggedIn");
        model.addAttribute("main", "main/default");
        return "view";
    }

    @GetMapping("/info")
    public String getInfo(Model model, HttpSession session) {
        int idx = (int) session.getAttribute("idx");
        Member member = memberService.select(idx);

        GetInfoResponse getInfoResponse = memberService.selectInfo(member);
        model.addAttribute("getInfoResponse", getInfoResponse);
        model.addAttribute("main", VIEW_PREFIX + "info");
        return "view";
    }

    @PostMapping("/info/update")
    public String postUpdate(PostUpdateInfo postUpdateInfo) {

        String password = postUpdateInfo.getPassword();
        String encryptPassword = bCryptPasswordEncoder.encode(password);

        memberService.update(postUpdateInfo.toEntity(encryptPassword));

        return "redirect:/member/info";
    }

    @PostMapping("/withdrawal")
    public PostWithdrawalResponse postWithdrawal(PostWithdrawal postWithdrawal) {
        PostWithdrawalResponse postWithdrawalResponse = new PostWithdrawalResponse();
        try {
            int idx = postWithdrawal.getIdx();

            memberService.delete(idx);
            postWithdrawalResponse.setCode(SuccessCode.member.withdrawalMember.getCode());
            postWithdrawalResponse.setMessage(SuccessCode.member.withdrawalMember.getMessage());
        } catch (Exception e) {
            postWithdrawalResponse.setCode(ErrorCode.member.withdrawalMember.getCode());
            postWithdrawalResponse.setMessage(ErrorCode.member.withdrawalMember.getMessage());
        }
        return postWithdrawalResponse;
    }
}
