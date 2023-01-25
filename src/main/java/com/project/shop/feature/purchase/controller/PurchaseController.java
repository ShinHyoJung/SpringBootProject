package com.project.shop.feature.purchase.controller;

import com.project.shop.feature.cart.entity.Cart;
import com.project.shop.feature.manage.category.entity.Category;
import com.project.shop.feature.manage.category.service.CategoryService;
import com.project.shop.feature.manage.product.entity.Product;
import com.project.shop.feature.manage.product.service.ProductService;
import com.project.shop.feature.member.service.MemberService;
import com.project.shop.feature.page.Paging;
import com.project.shop.feature.parcel.dto.PostAddParcel;
import com.project.shop.feature.parcel.service.ParcelService;
import com.project.shop.feature.purchase.dto.*;
import com.project.shop.feature.purchase.entity.Purchase;
import com.project.shop.feature.purchase.service.PurchaseService;
import com.project.shop.feature.sell.service.SellService;
import com.project.shop.feature.util.DateUtils;
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
import java.util.*;

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
    private final CategoryService categoryService;
    private final ProductService productService;

    @PostMapping("/pay")
    public String getPay(Model model, PostPayment postPayment,  HttpSession session) throws SQLException {
        int idx = (int) session.getAttribute("idx");
        Member member = memberService.selectByIdx(idx);
        Sell sell = sellService.select(postPayment.getSellID());

        List<Category> categoryList = categoryService.selectAll();

        model.addAttribute("menu", "sell");
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("quantity", postPayment.getQuantity());
        model.addAttribute("sell", sell);
        model.addAttribute("member", member);
        model.addAttribute("main", VIEW_PREFIX + "payment");
        return "view";
    }

    @PostMapping("/do")
    public String postDoPay(Model model, PostDoPay postDoPay, HttpSession session) throws ParseException, IOException, SQLException {
        purchaseService.insert(postDoPay.toEntity());

        Product product = productService.select(postDoPay.getProductID());
        int soldQuantity = product.getSoldQuantity();
        int leftQuantity = product.getLeftQuantity();

        soldQuantity += postDoPay.getQuantity();
        leftQuantity -= postDoPay.getQuantity();

        product.setSoldQuantity(soldQuantity);
        product.setLeftQuantity(leftQuantity);
        productService.update(product);

        int purchaseID = purchaseService.selectMaxPurchaseID();
        String waybillNumber = parcelService.makeWaybillNumber();
        Purchase purchase = purchaseService.selectByPurchaseID(purchaseID);

        PostAddParcel postAddParcel = new PostAddParcel();
        postAddParcel.setName(postDoPay.getName());
        postAddParcel.setIdx(postDoPay.getIdx());
        postAddParcel.setAddress(postDoPay.getAddress());
        postAddParcel.setDetailAddress(postDoPay.getDetailAddress());
        postAddParcel.setZipCode(postDoPay.getZipCode());
        postAddParcel.setQuantity(postDoPay.getQuantity());
        postAddParcel.setStatus(0);
        postAddParcel.setPurchaseID(purchaseID);
        postAddParcel.setSellID(postDoPay.getSellID());
        postAddParcel.setProductID(postDoPay.getProductID());
        postAddParcel.setPurchaseDate(purchase.getPurchaseDate());
        postAddParcel.setWaybillNumber(waybillNumber);

        parcelService.insert(postAddParcel.toEntity());

        session.removeAttribute("cartList");
        model.addAttribute("main", VIEW_PREFIX + "complete");
        return "view";
    }

    @GetMapping("/ordered")
    public String getOrdered(Model model) {
        model.addAttribute("menu", "user");
        model.addAttribute("main", VIEW_PREFIX + "ordered");
        return "view";
    }

    @ResponseBody
    @PostMapping("/ordered/list")
    public PostOrderedListResponse postOrderedList(@RequestBody PostOrderedList postOrderedList, HttpSession session) {
        PostOrderedListResponse pageResponse = new PostOrderedListResponse();
        try {
            int idx = (int) session.getAttribute("loggedIn");
            int total = purchaseService.count(idx);
            Paging paging = new Paging(postOrderedList.getCurrentPage(), 5, total);
            List<Purchase> purchaseList = purchaseService.selectByIdx(idx, paging);

            if (purchaseList != null) {
                for (Purchase purchase : purchaseList) {
                    Date purchaseDate = purchase.getPurchaseDate();
                    long diffDays = DateUtils.getDayDifference(purchaseDate, new Date());

                    if (diffDays > 30) {
                        purchaseService.delete(purchase.getPurchaseID());
                    } else {
                        if(diffDays > 7) {
                            purchaseService.updateOrdered("deliveryCompleted", "N", purchase.getPurchaseID());
                        } else {
                            if (diffDays == 0) {
                                purchase.setOrderStatus("paymentComplete");
                                purchaseService.updateOrdered("paymentComplete", "Y", purchase.getPurchaseID());
                            } else if (diffDays == 1) {
                                purchase.setOrderStatus("preparingForDelivery");
                                purchaseService.updateOrdered("preparingForDelivery", "Y", purchase.getPurchaseID());
                            } else if (diffDays == 2) {
                                purchase.setOrderStatus("shipping");
                                purchaseService.updateOrdered("shipping", "Y", purchase.getPurchaseID());
                            } else if (diffDays == 3) {
                                purchase.setOrderStatus("deliveryCompleted");
                                purchaseService.updateOrdered("deliveryCompleted", "Y", purchase.getPurchaseID());
                            }
                        }
                    }
                }
                pageResponse.setCode("SUCCESS");
                pageResponse.setMessage("주문내역이 있습니다.");
                pageResponse.setPurchaseList(purchaseList);
                pageResponse.setPaging(paging);
            } else {
                pageResponse.setCode("FAIL");
                pageResponse.setMessage("주문내역이 없습니다.");
            }
        } catch (Exception e) {
            pageResponse.setCode("FAIL");
            pageResponse.setMessage("주문내역이 없습니다.");
        }
        return pageResponse;
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
            parcelService.deleteByPurchaseID(postCancelOrdered.getPurchaseID());
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
        int idx = (int)session.getAttribute("loggedIn");
        Member member = memberService.selectByIdx(idx);

        model.addAttribute("member", member);
        model.addAttribute("menu", "user");
        model.addAttribute("main", VIEW_PREFIX + "cart");
        return "view";
    }

    @ResponseBody
    @PostMapping("/cart")
    public PostCartListResponse postCartList(HttpSession session) {
        PostCartListResponse pageResponse = new PostCartListResponse();
        int idx = (int)session.getAttribute("loggedIn");
        Member member = memberService.selectByIdx(idx);
        ArrayList<Cart> cartList = (ArrayList<Cart>)session.getAttribute("cartList");
        int totalPrice = 0;

        if(cartList != null) {
            for(Cart cart : cartList) {
                totalPrice += Integer.parseInt(cart.getPrice()) * cart.getQuantity();
            }

            pageResponse.setCartList(cartList);
            pageResponse.setMember(member);
            pageResponse.setTotalPrice(totalPrice);
            pageResponse.setCode("SUCCESS");
            pageResponse.setMessage("장바구니 목록이 있습니다.");
        } else {
            pageResponse.setCode("FAIL");
            pageResponse.setMessage("장바구니가 비었습니다.");
        }
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
