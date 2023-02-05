package com.project.shop.feature.login.controller;

import com.project.shop.feature.login.dto.PostLoginResponse;
import com.project.shop.feature.login.service.LoginService;
import com.project.shop.feature.manage.category.entity.Category;
import com.project.shop.feature.login.dto.PostLogin;
import com.project.shop.feature.manage.category.service.CategoryService;
import com.project.shop.feature.member.entity.Member;
import com.project.shop.feature.member.service.MemberService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController {

    private static final String VIEW_PREFIX = "login/";
    private final CategoryService categoryService;

    @GetMapping("/")
    public String getLogin(Model model) {
        List<Category> categoryList = categoryService.selectAll();

        model.addAttribute("categoryList", categoryList);
        model.addAttribute("menu", "sell");
        model.addAttribute("main", VIEW_PREFIX + "default");
        return "view";
    }
/*
    @ResponseBody
    @PostMapping("/do")
    public PostLoginResponse postLogin(@RequestBody PostLogin postLogin, HttpSession session) {
        PostLoginResponse pageResponse = new PostLoginResponse();
        try {
            boolean isValidate = loginService.isValidateIDPWD(postLogin.getLoginID(), postLogin.getPassword());

            if(isValidate) {
                Member member = memberService.selectByLoginID(postLogin.getLoginID());
                int idx = member.getIdx();
                session.setAttribute("loggedIn", idx);

                pageResponse.setCode("SUCCESS");
                pageResponse.setMessage("로그인이 성공적으로 완료되었습니다.");
            } else {
                pageResponse.setCode("FAIL");
                pageResponse.setMessage("아이디 또는 비밀번호가 일치하지 않습니다. 다시 시도해주세요.");
            }
        } catch (Exception e) {
            pageResponse.setCode("FAIL");
            pageResponse.setMessage("로그인을 실패하였습니다. 다시 시도해주세요.");
        }
        return pageResponse;
    }
*/

    @GetMapping("/logout")
    public String postLogout(Model model, HttpSession session) {
        model.addAttribute("main", "main/default");
        return "view";
    }
}
