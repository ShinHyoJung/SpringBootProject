package com.project.shop.feature.purchase.service;

import com.project.shop.feature.page.Paging;
import com.project.shop.feature.purchase.entity.Purchase;

import java.util.List;

public interface PurchaseService {

    void insert(List<Purchase> purchaseList);

    List<Purchase> selectByIdx(int idx, Paging paging);

    void delete(int purchaseID);

    Purchase selectByPurchaseID(int purchaseID);

    int selectMaxPurchaseID();

    void updateOrdered(String orderStatus, String availableCancelYN, int purchaseID);

    int count(int idx);
}
