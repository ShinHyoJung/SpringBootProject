package com.project.shop.feature.sell.service;

import com.project.shop.feature.sell.entity.Sell;

import java.util.List;

public interface SellService {

    void insert(Sell sell);

    List<Sell> selectAll();

    Sell select(int sellID);

    void delete(int sellID);
}
