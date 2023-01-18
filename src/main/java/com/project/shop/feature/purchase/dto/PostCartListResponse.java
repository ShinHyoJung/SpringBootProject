package com.project.shop.feature.purchase.dto;

import com.project.shop.feature.cart.entity.Cart;
import com.project.shop.feature.member.entity.Member;
import lombok.Data;
import lombok.NonNull;

import java.util.List;

/**
 * <pre>
 * Created with IntelliJ IDEA.
 * Company: NANDSOFT
 * User: sljh1020
 * Date: 2023-01-17
 * Comments:
 * </pre>
 */
@Data
public class PostCartListResponse {
    List<Cart> cartList;
    Member member;
    int totalPrice;
}
