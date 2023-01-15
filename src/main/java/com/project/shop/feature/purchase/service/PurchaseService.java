package com.project.shop.feature.purchase.service;

import com.project.shop.feature.purchase.entity.Purchase;

import java.util.List;

public interface PurchaseService {

    void insert(Purchase purchase);

    List<Purchase> select(int idx);
}
