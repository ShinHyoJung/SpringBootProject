package com.project.shop.feature.inquiry.controller;

import com.project.shop.feature.inquiry.dto.*;
import com.project.shop.feature.inquiry.entity.Inquiry;
import com.project.shop.feature.inquiry.service.InquiryService;
import com.project.shop.feature.member.entity.Member;
import com.project.shop.feature.member.service.MemberService;
import com.project.shop.feature.page.Paging;
import com.project.shop.feature.inquiry.dto.PostPrintList;
import lombok.RequiredArgsConstructor;
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

    @GetMapping("/")
    public String getInquiry(Model model) {
        model.addAttribute("menu", "user");
        model.addAttribute("main", VIEW_PREFIX + "default");
        return "view";
    }
    @ResponseBody
    @PostMapping("/list")
    public PostPrintListResponse postInquiry(@RequestBody PostPrintList postPrintList) {
        int total = inquiryService.count();

        Paging paging = new Paging(postPrintList.getCurrentPage(), 5, total);
        List<Inquiry> inquiryList = inquiryService.selectAll(paging);

        return new PostPrintListResponse(paging, inquiryList);
    }

    @GetMapping("/write")
    public String getWrite(Model model, HttpSession session) {
        int idx = (int)session.getAttribute("loggedIn");

        if(Integer.valueOf(idx) != null) {
            String loginID = session.getAttribute("loggedIn").toString();

            Member member = memberService.selectByIdx(idx);

            GetWriteResponse pageResponse = new GetWriteResponse();
            pageResponse.setIdx(idx);
            pageResponse.setLoginID(loginID);
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
        Inquiry inquiry = inquiryService.select(inquiryID);
        GetReadResponse pageResponse = new GetReadResponse();
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

    @GetMapping("/delete")
    public String getDelete(PostDelete postDelete) {
        inquiryService.delete(postDelete.getInquiryID());
        return "redirect:/board/";
    }

    @GetMapping("/update/{inquiryID}")
    public String getUpdate(@PathVariable("inquiryID")int inquiryID, Model model) {
        Inquiry board = inquiryService.select(inquiryID);

        GetUpdateResponse pageResponse = new GetUpdateResponse();
        pageResponse.setInquiryID(inquiryID);
        pageResponse.setTitle(board.getTitle());
        pageResponse.setContent(board.getContent());
        pageResponse.setWriter(board.getWriter());

        model.addAttribute("getUpdateResponse", pageResponse);
        model.addAttribute("main", VIEW_PREFIX + "update");
        return "view";
    }

    @PostMapping("/update")
    public String postUpdate(PostUpdate postUpdate) {
        inquiryService.update(postUpdate.toEntity());
        return "redirect:/board/read/" + postUpdate.getInquiryID();
    }
}
