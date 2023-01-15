package com.project.shop.feature.purchase.service.impl;

import com.project.shop.feature.purchase.dao.PurchaseDAO;
import com.project.shop.feature.purchase.entity.Purchase;
import com.project.shop.feature.purchase.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("PurchaseService")
@RequiredArgsConstructor
public class DefaultPurchaseService implements PurchaseService {
    private final PurchaseDAO purchaseDAO;

    @Override
    public void insert(Purchase purchase) {
        purchaseDAO.insert(purchase);
    }

    @Override
    public List<Purchase> select(int idx) {
        return purchaseDAO.select(idx);
    }
}
