package com.project.shop.feature.purchase.controller;

import com.project.shop.feature.cart.entity.Cart;
import com.project.shop.feature.cart.service.CartService;
import com.project.shop.feature.image.sellimage.entity.SellImage;
import com.project.shop.feature.image.sellimage.service.SellImageService;
import com.project.shop.feature.manage.category.dto.PostAdd;
import com.project.shop.feature.member.service.MemberService;
import com.project.shop.feature.parcel.dto.PostAddParcel;
import com.project.shop.feature.parcel.service.ParcelService;
import com.project.shop.feature.purchase.dto.*;
import com.project.shop.feature.purchase.entity.Purchase;
import com.project.shop.feature.purchase.service.PurchaseService;
import com.project.shop.feature.sell.service.SellService;
import com.project.shop.feature.web.rest.client.pay.service.PayService;
import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.project.shop.feature.member.entity.Member;
import javax.servlet.http.HttpSession;
import com.project.shop.feature.sell.entity.Sell;

import java.io.IOException;
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
    private final PurchaseService purchaseService;
    private final PayService payService;
    private final ParcelService parcelService;

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
    public String postDoPay(Model model, PostDoPay postDoPay, HttpSession session) throws ParseException, IOException {
        purchaseService.insert(postDoPay.toEntity());

        int purchaseID = purchaseService.selectMaxPurchaseID();

        PostAddParcel postAddParcel = new PostAddParcel();
        postAddParcel.setIdx(postDoPay.getIdx());
        postAddParcel.setAddress(postDoPay.getAddress());
        postAddParcel.setStatus(0);
        postAddParcel.setSellID(postDoPay.getSellID());
        postAddParcel.setPurchaseID(purchaseID);

        parcelService.insert(postAddParcel.toEntity());

        session.removeAttribute("cartList");
        model.addAttribute("main", VIEW_PREFIX + "complete");
        return "view";
    }

    @GetMapping("/ordered")
    public String getOrdered(Model model, HttpSession session) {
        int idx = (int)session.getAttribute("idx");
        List<Purchase> purchaseList = purchaseService.selectByIdx(idx);

        model.addAttribute("menu", "user");
        model.addAttribute("purchaseList", purchaseList);
        model.addAttribute("main", VIEW_PREFIX + "ordered");
        return "view";
    }

    @ResponseBody
    @PostMapping("/cancel")
    public PostCancelOrderedResponse postCancelOrdered(@RequestBody PostCancelOrdered postCancelOrdered) {
        PostCancelOrderedResponse pageResponse = new PostCancelOrderedResponse();
        try {
            Purchase purchase = purchaseService.selectByPurchaseID(postCancelOrdered.getPurchaseID());
            String accessToken = payService.getToken();
            int amount = payService.paymentInfo(purchase.getImpUid(), accessToken);

            payService.paymentCancel(purchase.getImpUid(), accessToken, amount, "관리자 취소");
            purchaseService.delete(postCancelOrdered.getPurchaseID());
            pageResponse.setCode("SUCCESS");
            pageResponse.setMessage("주문이 취소되었습니다.");
        } catch (Exception e) {
            pageResponse.setCode("FAIL");
            pageResponse.setMessage("주문 취소를 실패하였습니다.");
        }
        return pageResponse;
    }

    @ResponseBody
    @PostMapping("/cart/add")
    public PostAddCartResponse postAddCart(@RequestBody PostAddCart postAddCart, HttpSession session) {
        PostAddCartResponse pageResponse = new PostAddCartResponse();
        try {
            ArrayList<Cart> cartList = (ArrayList<Cart>)(session.getAttribute("cartList"));

            if(session.getAttribute("cartList") == null) {
                cartList = new ArrayList<Cart>();
            }

            Sell sell = sellService.select(postAddCart.getSellID());
            cartList.add(postAddCart.toEntity(sell.getThumbnailImageName()));
            pageResponse.setCode("SUCCESS");
            pageResponse.setMessage("장바구니에 추가되었습니다.");
            session.setAttribute("cartList", cartList);
        } catch (Exception e) {
            pageResponse.setCode("FAIL");
            pageResponse.setMessage("장바구니 추가를 실패하였습니다.");
        }
        return pageResponse;
    }

    @GetMapping("/cart")
    public String getCart(Model model, HttpSession session) {
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
        PostCartListResponse pageResponse = new PostCartListResponse();
        int idx = (int)session.getAttribute("idx");
        Member member = memberService.select(idx);
        ArrayList<Cart> cartList = (ArrayList<Cart>)session.getAttribute("cartList");
        int totalPrice = 0;

        for(Cart cart : cartList) {
            totalPrice += Integer.parseInt(cart.getPrice()) * cart.getQuantity();
        }

        pageResponse.setCartList(cartList);
        pageResponse.setMember(member);
        pageResponse.setTotalPrice(totalPrice);
        return pageResponse;
    }

    @ResponseBody
    @PostMapping("/cart/dump")
    public PostDumpCartResponse postDumpCart(@RequestBody PostDumpCart postDumpCart, HttpSession session) {
        PostDumpCartResponse pageResponse = new PostDumpCartResponse();
        int totalPrice = postDumpCart.getTotalPrice();
        try {
            ArrayList<Cart> cartList = (ArrayList<Cart>)session.getAttribute("cartList");

            totalPrice -= Integer.parseInt(cartList.get(postDumpCart.getSellID()).getPrice()) * cartList.get(postDumpCart.getSellID()).getQuantity();

            cartList.remove(postDumpCart.getSellID());

            pageResponse.setCode("SUCCESS");
            pageResponse.setMessage("장바구니에 해당 항목이 삭제되었습니다.");
        } catch (Exception e) {
            pageResponse.setCode("FAIL");
            pageResponse.setMessage("장바구니에서 삭제를 실패하였습니다.");
        }
        pageResponse.setTotalPrice(totalPrice);
       return pageResponse;
    }
}
