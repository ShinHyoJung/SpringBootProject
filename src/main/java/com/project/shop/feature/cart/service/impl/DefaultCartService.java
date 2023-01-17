package com.project.shop.feature.cart.service.impl;

import com.project.shop.feature.cart.dao.CartDAO;
import com.project.shop.feature.cart.entity.Cart;
import com.project.shop.feature.cart.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
@Service("CartService")
@RequiredArgsConstructor
public class DefaultCartService implements CartService {

    private final CartDAO cartDAO;
    @Override
    public void insert(Cart cart) {
        cartDAO.insert(cart);
    }
}
