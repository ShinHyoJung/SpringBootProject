package com.project.shop.feature.sell.service.impl;

import com.project.shop.feature.sell.dao.mapper.SellMapper;
import com.project.shop.feature.sell.entity.Sell;
import com.project.shop.feature.sell.service.SellService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("SellService")
@RequiredArgsConstructor
public class DefaultSellService implements SellService {

    private final SellMapper sellMapper;

    @Override
    public void insert(Sell sell) {
        sellMapper.insert(sell);
    }

    @Override
    public List<Sell> selectAll() {
        return sellMapper.selectAll();
    }

    @Override
    public Sell select(int sellID) {
        return sellMapper.select(sellID);
    }

    @Override
    public void delete(int sellID) {
        sellMapper.delete(sellID);
    }
}
