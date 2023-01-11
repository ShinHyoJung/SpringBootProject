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
    public List<Sell> selectAll(Paging paging) {
        return sellDAO.selectAll(paging);
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
    public int count() {
        return sellDAO.count();
    }

    @Override
    public void update(Sell sell) {
        sellDAO.update(sell);
    }

    @Override
    public int selectMaxSellID() {
        return sellDAO.selectMaxSellID();
    }
}
