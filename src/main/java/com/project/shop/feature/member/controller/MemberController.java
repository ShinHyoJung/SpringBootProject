package com.project.shop.feature.member.controller;

import com.project.shop.feature.member.dto.PostLogin;
import com.project.shop.feature.member.dto.PostSignUp;
import com.project.shop.feature.member.service.MemberService;
import lombok.RequiredArgsConstructor;
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
}
