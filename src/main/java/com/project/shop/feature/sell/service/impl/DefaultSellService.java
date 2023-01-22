package com.project.shop.feature.sell.service.impl;

import com.project.shop.feature.page.Paging;
import com.project.shop.feature.sell.dao.SellDAO;
import com.project.shop.feature.sell.entity.Sell;
import com.project.shop.feature.sell.service.SellService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("SellService")
@RequiredArgsConstructor
public class DefaultSellService implements SellService {

    private final SellDAO sellDAO;

    @Override
    public void insert(Sell sell) {
        sellDAO.insert(sell);
    }

    @Override
    public List<Sell> selectAll(Paging paging, String category, String searchOption, String keyword) {
        return sellDAO.selectAll(paging, category, searchOption, keyword);
    }

    @Override
    public Sell select(int sellID) {
        return sellDAO.select(sellID);
    }

    @Override
    public void delete(int sellID) {
        sellDAO.delete(sellID);
    }

    @Override
    public int count(String category, String searchOption, String keyword) {
        return sellDAO.count(category, searchOption, keyword);
    }

    @Override
    public void update(Sell sell) {
        sellDAO.update(sell);
    }

    @Override
    public int selectMaxSellID() {
        return sellDAO.selectMaxSellID();
    }

    @Override
    public void deleteByProductID(int productID) {
        sellDAO.deleteByProductID(productID);
    }
}
