package com.project.shop.feature.member.controller;

import com.project.shop.feature.auth.entity.Auth;
import com.project.shop.feature.auth.service.AuthService;
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
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private static final String VIEW_PREFIX = "member/";
    private final MemberService memberService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final CategoryService categoryService;
    private final AuthService authService;

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

            Member member = new Member();
            member.setLoginID(postSignUp.getLoginID());
            member.setAuthorities(AuthorityUtils.createAuthorityList("ROLE_USER"));
            authService.insert(member);
        } catch (Exception e) {

        }
        model.addAttribute("main", "main/default");
        return "view";
    }

    @ResponseBody
    @PostMapping("/check-duplicate")
    public PostCheckDuplicateResponse postCheckDuplicate(@RequestBody PostCheckDuplicate postCheckDuplicate) {
        PostCheckDuplicateResponse pageResponse = new PostCheckDuplicateResponse();

        boolean isDuplicate = memberService.isDuplicateLoginID(postCheckDuplicate.getLoginID());

        if(isDuplicate) {
            pageResponse.setCode("FAIL");
            pageResponse.setMessage("사용중인 아이디 입니다.");
        } else {
            pageResponse.setCode("SUCCESS");
            pageResponse.setMessage("사용가능한 아이디 입니다.");
        }
        return pageResponse;
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("/info")
    public String getInfo(Model model) {
        int idx = memberService.selectIdxByUsername();
        Member member = memberService.select(idx);

        model.addAttribute("member", member);
        model.addAttribute("menu", "user");
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
    public String getWithdrawal(Model model) {
        PostWithdrawalResponse pageResponse = new PostWithdrawalResponse();
        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String username = ((UserDetails)principal).getUsername();
            int idx = memberService.selectIdxByUsername();

            if(Integer.valueOf(idx) != null) {
                memberService.delete(idx);
                authService.delete(username);

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
            Member member = memberService.validateMember(postValidateMember.getName(), postValidateMember.getBirth());

            if(postValidateMember.getEmail().equals(member.getEmail())) {
                pageResponse.setCode("SUCCESS");
                pageResponse.setMessage("본인인증이 완료되었습니다.");
                pageResponse.setIdx(member.getIdx());
            } else {
                pageResponse.setCode("FAIL");
                pageResponse.setMessage("등록된 사용자의 이메일이 아닙니다. 다시 입력해주세요.");
            }
        } catch (Exception e) {
            pageResponse.setCode("FAIL");
            pageResponse.setMessage("등록된 사용자가 존재하지 않습니다. 다시 시도해주세요.");
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

    @ResponseBody
    @PostMapping("/id/validate")
    public PostValidateLoginIDResponse postValidateLoginID(@RequestBody PostValidateLoginID postValidateLoginID) {
        PostValidateLoginIDResponse pageResponse = new PostValidateLoginIDResponse();
        boolean isExist = memberService.isExistLoginID(postValidateLoginID.getLoginID());

        if(isExist) {
            pageResponse.setCode("SUCCESS");
            pageResponse.setMessage("아이디 검증을 완료했습니다.");
        } else {
            pageResponse.setCode("FAIL");
            pageResponse.setMessage("존재하지 않는 아이디 입니다.");
        }
        return pageResponse;
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
    public String getChangePwd(Model model, @ModelAttribute GetChangePassword getChangePassword) {
        List<Category> categoryList = categoryService.selectAll();

        model.addAttribute("idx", getChangePassword.getIdx());
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("menu", "sell");
        model.addAttribute("main", VIEW_PREFIX + "password/found");
        return "view";
    }

    @ResponseBody
    @PostMapping("/password/change")
    public PostChangePasswordResponse postChangePwd(@RequestBody PostChangePassword postChangePassword) {
        PostChangePasswordResponse pageResponse = new PostChangePasswordResponse();
        try {
            Member member = memberService.selectByIdx(postChangePassword.getIdx());

            String password = postChangePassword.getPassword();

            if(!bCryptPasswordEncoder.matches(password, member.getPassword())) {
                String encryptPassword = bCryptPasswordEncoder.encode(password);
                memberService.updatePassword(encryptPassword, postChangePassword.getIdx());
                pageResponse.setCode("SUCCESS");
                pageResponse.setMessage("비밀번호 변경을 완료하였습니다. 다시 로그인해주세요.");
            } else {
                pageResponse.setCode("FAIL");
                pageResponse.setMessage("이전과 동일한 비밀번호를 사용할 수 없습니다. 다른 비밀번호로 설정해 주세요.");
            }
        } catch (Exception e) {
            pageResponse.setCode("FAIL");
            pageResponse.setMessage("비밀번호 변경을 실패하였습니다. 다시 시도해주세요.");
        }
        return pageResponse;
    }
}
