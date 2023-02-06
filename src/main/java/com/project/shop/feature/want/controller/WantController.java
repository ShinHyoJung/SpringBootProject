package com.project.shop.feature.want.controller;

import com.project.shop.feature.member.entity.Member;
import com.project.shop.feature.member.service.MemberService;
import com.project.shop.feature.page.Paging;
import com.project.shop.feature.sell.entity.Sell;
import com.project.shop.feature.sell.service.SellService;
import com.project.shop.feature.want.dto.*;
import com.project.shop.feature.want.service.WantService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import com.project.shop.feature.want.entity.Want;
import java.util.List;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@RequestMapping("/want")
public class WantController {

    private final static String VIEW_PREFIX = "want/";
    private final WantService wantService;
    private final SellService sellService;
    private final MemberService memberService;

    @Secured({"ROLE_USER"})
    @GetMapping("/")
    public String getWant(Model model) {
        model.addAttribute("menu", "user");
        model.addAttribute("main", VIEW_PREFIX + "default");
        return "view";
    }

    @ResponseBody
    @PostMapping("/list")
    public PostPrintListResponse postPrintWantList(@RequestBody PostPrintList postPrintList) {
        PostPrintListResponse pageResponse = new PostPrintListResponse();
        try {
            int idx = memberService.selectIdxByUsername();

            int total = wantService.count(idx);
            Paging paging = new Paging(postPrintList.getCurrentPage(), 5, total);

            List<Want> wantList = wantService.selectAll(idx, paging);

            if(wantList.isEmpty()) {
              pageResponse.setCode("FAIL");
              pageResponse.setMessage("찜한 내역이 비어있습니다.");
            } else {
                pageResponse.setWantList(wantList);
                pageResponse.setPaging(paging);
                pageResponse.setCode("SUCCESS");
                pageResponse.setMessage("찜한 내역입니다.");
            }
        } catch (Exception e) {
            pageResponse.setCode("FAIL");
            pageResponse.setMessage("찜한 내역이 비어있습니다.");
        }
        return pageResponse;
    }

    @ResponseBody
    @PostMapping("/add")
    public PostAddWantResponse postAddWant(@RequestBody PostAddWant postAddWant) {
        PostAddWantResponse pageResponse = new PostAddWantResponse();
        try {
            int idx = memberService.selectIdxByUsername();

            Sell sell = sellService.select(postAddWant.getSellID());
            wantService.insert(postAddWant.toEntity(idx, sell.getThumbnailImageName()));

            pageResponse.setCode("SUCCESS");
            pageResponse.setMessage("찜한내역에 추가되었습니다.");
        } catch (Exception e) {
            pageResponse.setCode("FAIL");
            pageResponse.setMessage("찜한 내역 추가를 실패하였습니다. 다시 시도해주세요.");
        }
        return pageResponse;
    }

    @ResponseBody
    @PostMapping("/delete")
    public PostDeleteWantResponse postDeleteWant(@RequestBody PostDeleteWant postDeleteWant) {
        PostDeleteWantResponse pageResponse = new PostDeleteWantResponse();
        try {
            int idx = memberService.selectIdxByUsername();

            wantService.delete(postDeleteWant.getSellID(), idx);

            pageResponse.setCode("SUCCESS");
            pageResponse.setMessage("찜한 내역에서 삭제되었습니다.");
        } catch (Exception e) {
            pageResponse.setCode("FAIL");
            pageResponse.setMessage("찜한 내역에서 삭제를 실패하였습니다. 다시 시도해주세요.");
        }
        return pageResponse;
    }
}
