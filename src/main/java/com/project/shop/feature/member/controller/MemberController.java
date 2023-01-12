package com.project.shop.feature.member.controller;

import com.project.shop.feature.code.error.ErrorCode;
import com.project.shop.feature.code.success.SuccessCode;
import com.project.shop.feature.member.dto.*;
import com.project.shop.feature.member.entity.Member;
import com.project.shop.feature.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

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
        return "redirect:/member/info";
    }

    @GetMapping("/info/download")
    public void postDownloadInfo(HttpServletResponse response, @ModelAttribute GetDownloadInfo getDownloadInfo) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("개인정보");
        int rowNo = 0;

        Row headerRow = sheet.createRow(rowNo++);
        headerRow.createCell(0).setCellValue("아이디");
        headerRow.createCell(1).setCellValue("이름");
        headerRow.createCell(2).setCellValue("생년월일");
        headerRow.createCell(3).setCellValue("휴대폰번호");
        headerRow.createCell(4).setCellValue("이메일");
        headerRow.createCell(5).setCellValue("주소");

        Member member = memberService.select(getDownloadInfo.getIdx());
        Row row = sheet.createRow(rowNo++);
        row.createCell(0).setCellValue(member.getMemberID());
        row.createCell(1).setCellValue(member.getName());
        row.createCell(2).setCellValue(member.getBirth());
        row.createCell(3).setCellValue(member.getMobile());
        row.createCell(4).setCellValue(member.getMail());
        row.createCell(5).setCellValue(member.getAddress());

        response.setContentType("ms-vnd/excel");
        response.setHeader("Content-Disposition", "attachment;filename=info.xls");

        File xlsFile = new File("C:/info.xls");
        FileOutputStream fileOut = new FileOutputStream(xlsFile);
        workbook.write(fileOut);
        workbook.close();
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
