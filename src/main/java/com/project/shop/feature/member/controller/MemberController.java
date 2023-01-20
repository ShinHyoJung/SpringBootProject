package com.project.shop.feature.member.controller;

import com.project.shop.feature.authentication.method.email.dto.PostSendEmailResponse;
import com.project.shop.feature.code.error.ErrorCode;
import com.project.shop.feature.code.success.SuccessCode;
import com.project.shop.feature.manage.category.entity.Category;
import com.project.shop.feature.manage.category.service.CategoryService;
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
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private static final String VIEW_PREFIX = "member/";
    private final MemberService memberService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final CategoryService categoryService;

    @GetMapping("/signUp") // 회원가입 페이지
    public String getSignUp(Model model) {
        List<Category> categoryList = categoryService.selectAll();

        model.addAttribute("categoryList", categoryList);
        model.addAttribute("menu", "sell");
        model.addAttribute("main", VIEW_PREFIX + "signUp");
        return "view";
    }

    @PostMapping("/signUp") // 회원가입
    public String postSignUp(Model model, PostSignUp postSignUp) {
        try {
            String password = postSignUp.getPassword();
            String encryptPassword = bCryptPasswordEncoder.encode(password);
            memberService.insert(postSignUp.toEntity(encryptPassword));
        } catch (Exception e) {

        }
        model.addAttribute("main", "main/default");
        return "view";
    }

    @ResponseBody
    @PostMapping("/check-duplicate")
    public PostCheckDuplicateResponse postCheckDuplicate(@RequestBody PostCheckDuplicate postCheckDuplicate) {
        PostCheckDuplicateResponse pageResponse = new PostCheckDuplicateResponse();

        boolean isDuplicate = memberService.validateLoginID(postCheckDuplicate.getLoginID());

        if(isDuplicate) {
            pageResponse.setCode("FAIL");
            pageResponse.setMessage("사용중인 아이디 입니다.");
        } else {
            pageResponse.setCode("SUCCESS");
            pageResponse.setMessage("사용가능한 아이디 입니다.");
        }
        return pageResponse;
    }

    @GetMapping("/login")
    public String getLogin(Model model) {
        List<Category> categoryList = categoryService.selectAll();

        model.addAttribute("categoryList", categoryList);
        model.addAttribute("menu", "sell");
        model.addAttribute("main", VIEW_PREFIX + "login");
        return "view";
    }
    @PostMapping("/login")
    public String postLogin(Model model, HttpSession session, PostLogin postLogin) {
        boolean isValidate = memberService.isValidateIDPWD(postLogin);

        if(isValidate) {
            if(StringUtils.isNotEmpty(postLogin.getId())) {
                Member member = memberService.selectByLoginID(postLogin.getId());
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
        Member member = memberService.selectByIdx(idx);

        GetInfoResponse getInfoResponse = memberService.selectInfo(member);
        model.addAttribute("menu", "user");
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

        Member member = memberService.selectByIdx(getDownloadInfo.getIdx());
        Row row = sheet.createRow(rowNo++);
        row.createCell(0).setCellValue(member.getLoginID());
        row.createCell(1).setCellValue(member.getName());
        row.createCell(2).setCellValue(member.getBirth());
        row.createCell(3).setCellValue(member.getPhone());
        row.createCell(4).setCellValue(member.getEmail());
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

    @GetMapping("/id/find")
    public String getFindId(Model model) {
        List<Category> categoryList = categoryService.selectAll();

        model.addAttribute("categoryList", categoryList);
        model.addAttribute("menu", "sell");
        model.addAttribute("main", VIEW_PREFIX + "id/find");
        return "view";
    }

    @ResponseBody
    @PostMapping("/validate")
    public PostValidateMemberResponse postValidateMember(@RequestBody PostValidateMember postValidateMember) {
        PostValidateMemberResponse pageResponse = new PostValidateMemberResponse();
        try {
            Member member = memberService.selectByNameAndBirth(postValidateMember.getName(), postValidateMember.getBirth());

            if(postValidateMember.getEmail().equals(member.getEmail())) {
                pageResponse.setCode("SUCCESS");
                pageResponse.setMessage("본인인증이 완료되었습니다.");
                pageResponse.setIdx(member.getIdx());
            } else {
                pageResponse.setCode("FAIL");
                pageResponse.setMessage("본인인증을 실패하였습니다. 다시 시도해주세요.");
            }
        } catch (Exception e) {
            pageResponse.setCode("FAIL");
            pageResponse.setMessage("본인인증을 실패하였습니다. 다시 시도해주세요.");
        }
        return pageResponse;
    }

    @ResponseBody
    @PostMapping("/id/find")
    public PostFindIdResponse postFindId(@RequestBody PostFindId postFindId) {
        PostFindIdResponse pageResponse = new PostFindIdResponse();
        Member member = memberService.selectByIdx(postFindId.getIdx());

        if(member == null) {
            pageResponse.setCode("FAIL");
            pageResponse.setMessage("아이디 찾기를 실패하였습니다.");
        } else {
            pageResponse.setCode("SUCCESS");
            pageResponse.setMessage("아이디 찾기를 성공하였습니다.");
            pageResponse.setLoginID(member.getLoginID());
        }
        return pageResponse;
    }

    @GetMapping("/id/found")
    public String getFoundId(Model model, @ModelAttribute GetFoundId getFoundId) {
        List<Category> categoryList = categoryService.selectAll();

        model.addAttribute("loginID", getFoundId.getLoginID());
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("menu", "sell");
        model.addAttribute("main", VIEW_PREFIX + "id/found");
        return "view";
    }

    @GetMapping("/password/find")
    public String getFindPwd(Model model) {
        List<Category> categoryList = categoryService.selectAll();

        model.addAttribute("categoryList", categoryList);
        model.addAttribute("menu", "sell");
        model.addAttribute("main", VIEW_PREFIX + "password/find");
        return "view";
    }

    @GetMapping("/password/change")
    public String getChangePwd(Model model) {
        List<Category> categoryList = categoryService.selectAll();

        model.addAttribute("categoryList", categoryList);
        model.addAttribute("menu", "sell");
        model.addAttribute("main", VIEW_PREFIX + "password/found");
        return "view";
    }
}
