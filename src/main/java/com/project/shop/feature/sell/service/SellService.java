package com.project.shop.feature.sell.service;

import com.project.shop.feature.page.Paging;
import com.project.shop.feature.sell.entity.Sell;

import java.util.List;
import java.util.Map;

public interface SellService {

    void insert(Sell sell);

    List<Sell> selectAll(Paging paging, String category, String searchOption, String keyword);

    Sell select(int sellID);

    void delete(int sellID);

    int count(String category, String searchOption, String keyword);

    void update(Sell sell);

    int selectMaxSellID();

    void deleteByProductID(int productID);
}
