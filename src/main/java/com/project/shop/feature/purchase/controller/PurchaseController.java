package com.project.shop.feature.purchase.controller;

import com.project.shop.feature.cart.entity.Cart;
import com.project.shop.feature.cart.service.CartService;
import com.project.shop.feature.image.sellimage.entity.SellImage;
import com.project.shop.feature.image.sellimage.service.SellImageService;
import com.project.shop.feature.member.service.MemberService;
import com.project.shop.feature.purchase.dto.*;
import com.project.shop.feature.purchase.entity.Purchase;
import com.project.shop.feature.purchase.service.PurchaseService;
import com.project.shop.feature.sell.service.SellService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.project.shop.feature.member.entity.Member;
import javax.servlet.http.HttpSession;
import com.project.shop.feature.sell.entity.Sell;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/purchase")
@RequiredArgsConstructor
public class PurchaseController {

    private static final String VIEW_PREFIX = "purchase/";
    private final MemberService memberService;
    private final SellService sellService;
    private final CartService cartService;
    private final PurchaseService purchaseService;

    @PostMapping("/pay")
    public String getPay(Model model, PostPayment postPayment,  HttpSession session) throws SQLException {
        int idx = (int) session.getAttribute("idx");
        Member member = memberService.select(idx);
        Sell sell = sellService.select(postPayment.getSellID());

        model.addAttribute("quantity", postPayment.getQuantity());
        model.addAttribute("sell", sell);
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

        model.addAttribute("menu", "user");
        model.addAttribute("purchaseList", purchaseList);
        model.addAttribute("main", VIEW_PREFIX + "ordered");
        return "view";
    }

    @ResponseBody
    @PostMapping("/cancel")
    public PostCancelOrderedResponse postCancelOrdered(@RequestBody PostCancelOrdered postCancelOrdered) {
        purchaseService.delete(postCancelOrdered.getPurchaseID());
        String code = "SUCCESS";
        return new PostCancelOrderedResponse(code);
    }

    @ResponseBody
    @PostMapping("/cart/add")
    public PostAddCartResponse postAddCart(@RequestBody PostAddCart postAddCart, HttpSession session) {
        //cartService.insert(postAddCart.toEntity());
        ArrayList<Cart> cartList = (ArrayList<Cart>)(session.getAttribute("cartList"));

        if(session.getAttribute("cartList") == null) {
            cartList = new ArrayList<Cart>();
        }

        Sell sell = sellService.select(postAddCart.getSellID());
        cartList.add(postAddCart.toEntity(sell.getThumbnailImageName()));
        session.setAttribute("cartList", cartList);
        String code = "SUCCESS";
        return new PostAddCartResponse(code);
    }

    @GetMapping("/cart")
    public String getCart(Model model,HttpSession session) {
        int idx = (int)session.getAttribute("idx");
        Member member = memberService.select(idx);

        model.addAttribute("member", member);
        model.addAttribute("menu", "user");
        model.addAttribute("main", VIEW_PREFIX + "cart");
        return "view";
    }
    @ResponseBody
    @PostMapping("/cart")
    public PostCartListResponse postCartList(HttpSession session) {
        ArrayList<Cart> cartList = (ArrayList<Cart>)session.getAttribute("cartList");

        if(cartList == null) {

        }
        int totalPrice = 0;
        for(Cart cart : cartList) {
            totalPrice += Integer.parseInt(cart.getPrice()) * cart.getQuantity();
        }
        return new PostCartListResponse(cartList, totalPrice);
    }

    @ResponseBody
    @PostMapping("/cart/dump")
    public PostDumpCartResponse postDumpCart(@RequestBody PostDumpCart postDumpCart, HttpSession session) {
        ArrayList<Cart> cartList = (ArrayList<Cart>)session.getAttribute("cartList");
        cartList.remove(postDumpCart.getSellID());
        int totalPrice = postDumpCart.getTotalPrice();

        totalPrice -= Integer.parseInt(cartList.get(postDumpCart.getSellID()).getPrice()) * cartList.get(postDumpCart.getSellID()).getQuantity();

        return new PostDumpCartResponse(cartList, totalPrice);
    }
}
