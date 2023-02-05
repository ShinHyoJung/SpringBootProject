package com.project.shop.feature.inquiry.controller;

import com.project.shop.feature.inquiry.dto.*;
import com.project.shop.feature.inquiry.entity.Inquiry;
import com.project.shop.feature.inquiry.service.InquiryService;
import com.project.shop.feature.member.entity.Member;
import com.project.shop.feature.member.service.MemberService;
import com.project.shop.feature.page.Paging;
import com.project.shop.feature.inquiry.dto.PostPrintList;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("inquiry/")
public class InquiryController {

    private static final String VIEW_PREFIX = "inquiry/";

    private final MemberService memberService;
    private final InquiryService inquiryService;

    @Secured({"ROLE_USER"})
    @GetMapping("/")
    public String getInquiry(Model model) {
        model.addAttribute("menu", "user");
        model.addAttribute("main", VIEW_PREFIX + "default");
        return "view";
    }
    @ResponseBody
    @PostMapping("/list")
    public PostPrintListResponse postInquiry(@RequestBody PostPrintList postPrintList) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();
        Member member = memberService.selectByLoginID(username);
        int idx = member.getIdx();

        int total = inquiryService.countByIdx(idx);

        Paging paging = new Paging(postPrintList.getCurrentPage(), 5, total);
        List<Inquiry> inquiryList = inquiryService.selectAllByIdx(idx, paging);

        return new PostPrintListResponse(paging, inquiryList);
    }

    @GetMapping("/write")
    public String getWrite(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();
        Member memberForIdx = memberService.selectByLoginID(username);
        int idx = memberForIdx.getIdx();

        if(Integer.valueOf(idx) != null) {
            Member member = memberService.selectByIdx(idx);
            GetWriteResponse pageResponse = new GetWriteResponse();
            pageResponse.setIdx(idx);
            pageResponse.setLoginID(username);
            pageResponse.setWriter(member.getName());

            model.addAttribute("getWriteResponse", pageResponse);
            model.addAttribute("menu", "user");
            model.addAttribute("main", VIEW_PREFIX + "write");
        } else {
            model.addAttribute("main", "main/default");
        }
        return "view";
    }

    @PostMapping("/write")
    public String postWrite(PostWrite postWrite) {
        inquiryService.insert(postWrite.toEntity());
        return "redirect:/inquiry/";
    }

    @GetMapping("/read/{inquiryID}")
    public String getRead(@PathVariable("inquiryID")int inquiryID, Model model) {
        GetReadResponse pageResponse = new GetReadResponse();
        Inquiry inquiry = inquiryService.select(inquiryID);

        pageResponse.setTitle(inquiry.getTitle());
        pageResponse.setContent(inquiry.getContent());
        pageResponse.setWriter(inquiry.getWriter());
        pageResponse.setCreateDate(inquiry.getCreateDate());
        pageResponse.setUpdateDate(inquiry.getUpdateDate());
        pageResponse.setInquiryID(inquiry.getInquiryID());

        model.addAttribute("getReadResponse", pageResponse);
        model.addAttribute("menu", "user");
        model.addAttribute("main", VIEW_PREFIX + "read");
        return "view";
    }

    @GetMapping("/delete/{inquiryID}")
    public String getDelete(@PathVariable("inquiryID")int inquiryID) {
        inquiryService.delete(inquiryID);
        return "redirect:/inquiry/";
    }

    @GetMapping("/update/{inquiryID}")
    public String getUpdate(Model model, @PathVariable("inquiryID")int inquiryID) {
        Inquiry board = inquiryService.select(inquiryID);

        GetUpdateResponse pageResponse = new GetUpdateResponse();
        pageResponse.setInquiryID(inquiryID);
        pageResponse.setTitle(board.getTitle());
        pageResponse.setContent(board.getContent());
        pageResponse.setWriter(board.getWriter());

        model.addAttribute("menu", "user");
        model.addAttribute("getUpdateResponse", pageResponse);
        model.addAttribute("main", VIEW_PREFIX + "update");
        return "view";
    }

    @PostMapping("/update")
    public String postUpdate(PostUpdate postUpdate) {
        inquiryService.update(postUpdate.toEntity());
        return "redirect:/inquiry/read/" + postUpdate.getInquiryID();
    }
}
