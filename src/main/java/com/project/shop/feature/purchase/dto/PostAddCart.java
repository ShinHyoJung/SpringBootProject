package com.project.shop.feature.purchase.dto;

import com.project.shop.feature.cart.entity.Cart;
import lombok.Data;

import java.util.ArrayList;
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
public class PostAddCart {
    private String name;
    private String price;
    private int quantity;
    private int sellID;

    public Cart toEntity(String thumbnailImageName) {
        Cart cart = new Cart();
        cart.setName(this.name);
        cart.setPrice(this.price);
        cart.setQuantity(this.quantity);
        cart.setSellID(this.getSellID());
        cart.setThumbnailImageName(thumbnailImageName);
        return cart;
    }
}
