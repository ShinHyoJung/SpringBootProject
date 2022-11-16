package com.project.shop.feature.member.controller;

import com.project.shop.feature.member.dto.PostSignUp;
import com.project.shop.feature.member.entity.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class MemberController {

    private static final String VIEW_PREFIX = "member/";

    @GetMapping("/signUp")
    public String getSignUp(Model model) {
        model.addAttribute("main", VIEW_PREFIX + "signUp");
        return "view";
    }

    @PostMapping("/signUp")
    public String postSignUp(Model model, PostSignUp postSignUp) {

    }
}
