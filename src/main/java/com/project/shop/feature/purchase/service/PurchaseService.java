package com.project.shop.feature.purchase.service;

import com.project.shop.feature.purchase.entity.Purchase;

import java.util.List;

public interface PurchaseService {

    void insert(List<Purchase> purchaseList);

    List<Purchase> selectByIdx(int idx);

    void delete(int purchaseID);

    Purchase selectByPurchaseID(int purchaseID);

    int selectMaxPurchaseID();
}
