package com.project.shop.feature.want.service.impl;

import com.project.shop.feature.page.Paging;
import com.project.shop.feature.want.dao.WantDAO;
import com.project.shop.feature.want.entity.Want;
import com.project.shop.feature.want.service.WantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("WantService")
@RequiredArgsConstructor
public class DefaultWantService implements WantService {

    private final WantDAO wantDAO;

    @Override
    public void insert(Want want) {
        wantDAO.insert(want);
    }

    @Override
    public Want select(int sellID, int idx) {
        return wantDAO.select(sellID, idx);
    }

    @Override
    public List<Want> selectAll(int idx, Paging paging) {
        return wantDAO.selectAll(idx, paging);
    }

    @Override
    public void delete(int sellID, int idx) {
        wantDAO.delete(sellID, idx);
    }

    @Override
    public int count(int idx) {
        return wantDAO.count(idx);
    }
}
