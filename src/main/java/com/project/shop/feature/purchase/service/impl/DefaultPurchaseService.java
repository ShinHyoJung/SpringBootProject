package com.project.shop.feature.purchase.service.impl;

import com.project.shop.feature.page.Paging;
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
    public void insert(List<Purchase> purchaseList) {
        purchaseDAO.insert(purchaseList);
    }

    @Override
    public List<Purchase> selectByIdx(int idx, Paging paging) {
        return purchaseDAO.selectByIdx(idx, paging);
    }

    @Override
    public void delete(int purchaseID) {
        purchaseDAO.delete(purchaseID);
    }

    @Override
    public Purchase selectByPurchaseID(int purchaseID) {
        return purchaseDAO.selectByPurchaseID(purchaseID);
    }

    @Override
    public int selectMaxPurchaseID() {
        return purchaseDAO.selectMaxPurchaseID();
    }

    @Override
    public void updateOrdered(String orderStatus, String availableCancelYN, int purchaseID) {
        purchaseDAO.updateOrdered(orderStatus, availableCancelYN, purchaseID);
    }

    @Override
    public int count(int idx) {
        return purchaseDAO.count(idx);
    }
}
