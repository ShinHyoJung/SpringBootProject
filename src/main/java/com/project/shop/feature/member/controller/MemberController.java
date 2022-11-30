package com.project.shop.feature.member.controller;

import com.project.shop.feature.code.error.ErrorCode;
import com.project.shop.feature.code.success.SuccessCode;
import com.project.shop.feature.member.dto.*;
import com.project.shop.feature.member.entity.Member;
import com.project.shop.feature.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
            if(StringUtils.isNotEmpty(postLogin.getId())) {
                Member member = memberService.select(postLogin.getId());
                int idx = member.getIdx();
                session.setAttribute("idx", idx);
                session.setAttribute("loggedIn", postLogin.getId());
            }
        }
        model.addAttribute("main", "main/default");
        return "view";
    }

    @GetMapping("/logout")
    public String postLogout(Model model, HttpSession session) {
        try {
            session.removeAttribute("loggedIn");
        } catch (Exception e) {

        }
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
    public PostUpdateInfoResponse postUpdate(PostUpdateInfo postUpdateInfo) {
        PostUpdateInfoResponse postUpdateInfoResponse = new PostUpdateInfoResponse();
        try{
            String password = postUpdateInfo.getPassword();
            if(StringUtils.isNotEmpty(password)) {
                String encryptPassword = bCryptPasswordEncoder.encode(password);
                memberService.update(postUpdateInfo.toEntity(encryptPassword));

                postUpdateInfoResponse.setCode(SuccessCode.member.updateMember.getCode());
                postUpdateInfoResponse.setMessage(SuccessCode.member.updateMember.getMessageKey());
            }
        } catch (Exception e) {
            postUpdateInfoResponse.setCode(ErrorCode.member.updateMember.getCode());
            postUpdateInfoResponse.setMessage(ErrorCode.member.updateMember.getMessageKey());
        }
        return postUpdateInfoResponse;
    }



    @GetMapping("/withdrawal")
    public String getWithdrawal(Model model, HttpSession session) {
        PostWithdrawalResponse pageResponse = new PostWithdrawalResponse();
        try {
            int idx = (int)session.getAttribute("idx");
            if(Integer.valueOf(idx) != null) {
                memberService.delete(idx);
                session.removeAttribute("loggedIn");
                pageResponse.setCode(SuccessCode.member.withdrawalMember.getCode());
                pageResponse.setMessage(SuccessCode.member.withdrawalMember.getMessageKey());
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            pageResponse.setCode(ErrorCode.member.withdrawalMember.getCode());
            pageResponse.setMessage(ErrorCode.member.withdrawalMember.getMessageKey());
        }
        model.addAttribute("main", "main/default");
        return "view";
    }
}
