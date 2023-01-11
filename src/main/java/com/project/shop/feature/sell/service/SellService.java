package com.project.shop.feature.sell.service;

import com.project.shop.feature.page.Paging;
import com.project.shop.feature.sell.entity.Sell;

import java.util.List;
import java.util.Map;

public interface SellService {

    void insert(Sell sell);

    List<Sell> selectAll(Paging paging);

    Sell select(int sellID);

    void delete(int sellID);

    int count();

    void update(Sell sell);

    int selectMaxSellID();
}
