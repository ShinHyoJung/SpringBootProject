package com.project.shop.feature.inquiry.controller;

import com.project.shop.feature.answer.dto.PostDeleteResponse;
import com.project.shop.feature.answer.entity.Answer;
import com.project.shop.feature.answer.service.impl.AnswerService;
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
    private final AnswerService answerService;

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
        int idx = memberService.selectIdxByUsername();

        int total = inquiryService.countByIdx(idx);
        Paging paging = new Paging(postPrintList.getCurrentPage(), 5, total);
        List<Inquiry> inquiryList = inquiryService.selectAllByIdx(idx, paging);

        return new PostPrintListResponse(paging, inquiryList);
    }

    @GetMapping("/write")
    public String getWrite(Model model) {
        int idx = memberService.selectIdxByUsername();

        if(Integer.valueOf(idx) != null) {
            Member member = memberService.selectByIdx(idx);
            GetWriteResponse pageResponse = new GetWriteResponse();
            pageResponse.setIdx(idx);
            pageResponse.setLoginID(member.getLoginID());
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
        int idx = memberService.selectIdxByUsername();
        Inquiry inquiry = inquiryService.select(inquiryID);

        pageResponse.setTitle(inquiry.getTitle());
        pageResponse.setContent(inquiry.getContent());
        pageResponse.setWriter(inquiry.getWriter());
        pageResponse.setCreateDate(inquiry.getCreateDate());
        pageResponse.setUpdateDate(inquiry.getUpdateDate());
        pageResponse.setInquiryID(inquiry.getInquiryID());
        pageResponse.setIdx(inquiry.getIdx());

        try {
            Answer answer = answerService.select(inquiryID);
            model.addAttribute("answer", answer);
        } catch (Exception e) {

        }

        model.addAttribute("getReadResponse", pageResponse);
        model.addAttribute("idx", idx);
        model.addAttribute("menu", "manage");
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
        Inquiry inquiry = inquiryService.select(inquiryID);

        GetUpdateResponse pageResponse = new GetUpdateResponse();
        pageResponse.setInquiryID(inquiryID);
        pageResponse.setTitle(inquiry.getTitle());
        pageResponse.setContent(inquiry.getContent());
        pageResponse.setWriter(inquiry.getWriter());

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

    @GetMapping("/manage")
    public String getManageInquiry(Model model) {
        model.addAttribute("menu", "manage");
        model.addAttribute("main", VIEW_PREFIX + "manage");
        return "view";
    }

    @ResponseBody
    @PostMapping("/manage/list")
    public PostPrintManageListResponse postPrintManageList(@RequestBody PostPrintManageList postPrintManageList) {
        PostPrintManageListResponse pageResponse = new PostPrintManageListResponse();

        int total = inquiryService.count();
        Paging paging = new Paging(postPrintManageList.getCurrentPage(), 5, total);

        List<Inquiry> inquiryList = inquiryService.selectAll(paging);

        pageResponse.setPaging(paging);
        pageResponse.setInquiryList(inquiryList);
        return pageResponse;
    }

    @PostMapping("/manage/answer/write")
    public String postWriteAnswer(com.project.shop.feature.answer.dto.PostWrite postWrite) {
        int idx = postWrite.getIdx();
        Member member = memberService.selectByIdx(idx);

        String writer = member.getName();

        answerService.insert(postWrite.toEntity(writer));

        return "redirect:/inquiry/read/" + postWrite.getInquiryID();
    }

    @PostMapping("/manage/answer/update")
    public String postUpdateAnswer(com.project.shop.feature.answer.dto.PostUpdate postUpdate) {
        answerService.update(postUpdate.toEntity());

        return "redirect:/inquiry/read/" + postUpdate.getInquiryID();
    }

    @ResponseBody
    @PostMapping("/manage/answer/delete")
    public PostDeleteResponse postDeleteAnswer(@RequestBody com.project.shop.feature.answer.dto.PostDelete postDelete) {
        PostDeleteResponse postDeleteResponse = new PostDeleteResponse();
        try {
            answerService.delete(postDelete.getAnswerID());
            postDeleteResponse.setCode("SUCCESS");
            postDeleteResponse.setMessage("답변을 삭제하였습니다.");
        } catch (Exception e) {
            postDeleteResponse.setCode("FAIL");
            postDeleteResponse.setMessage("답변 삭제를 실패하였습니다.");
        }
        return postDeleteResponse;
    }
}
