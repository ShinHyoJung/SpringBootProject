package com.project.shop.feature.purchase.controller;

import com.project.shop.feature.image.entity.SellImage;
import com.project.shop.feature.image.service.SellImageService;
import com.project.shop.feature.member.service.MemberService;
import com.project.shop.feature.purchase.dto.PostDoPay;
import com.project.shop.feature.purchase.dto.PostPayment;
import com.project.shop.feature.purchase.entity.Purchase;
import com.project.shop.feature.purchase.service.PurchaseService;
import com.project.shop.feature.sell.service.SellService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.project.shop.feature.member.entity.Member;
import javax.servlet.http.HttpSession;
import com.project.shop.feature.sell.entity.Sell;

import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/purchase")
@RequiredArgsConstructor
public class PurchaseController {

    private static final String VIEW_PREFIX = "purchase/";
    private final MemberService memberService;
    private final SellService sellService;
    private final SellImageService sellImageService;
    private final PurchaseService purchaseService;

    @PostMapping("/pay")
    public String getPay(Model model, PostPayment postPayment,  HttpSession session) throws SQLException {
        int idx = (int) session.getAttribute("idx");
        Member member = memberService.select(idx);
        Sell sell = sellService.select(postPayment.getSellID());
        SellImage sellImage = sellImageService.select(postPayment.getSellID(), 0);

        model.addAttribute("quantity", postPayment.getQuantity());
        model.addAttribute("sell", sell);
        model.addAttribute("sellImage", sellImage);
        model.addAttribute("member", member);
        model.addAttribute("main", VIEW_PREFIX + "payment");
        return "view";
    }

    @PostMapping("/do")
    public String postDoPay(Model model, PostDoPay postDoPay) {
        purchaseService.insert(postDoPay.toEntity());
        model.addAttribute("main", VIEW_PREFIX + "complete");
        return "view";
    }

    @GetMapping("/ordered")
    public String getOrdered(Model model, HttpSession session) {
        int idx = (int)session.getAttribute("idx");
        List<Purchase> purchaseList = purchaseService.select(idx);

        model.addAttribute("purchaseList", purchaseList);
        model.addAttribute("main", VIEW_PREFIX + "ordered");
        return "view";
    }
}
