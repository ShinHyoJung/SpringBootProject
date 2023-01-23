package com.project.shop.feature.want.service;

import com.project.shop.feature.page.Paging;
import com.project.shop.feature.want.entity.Want;

import java.util.List;

public interface WantService {

    void insert(Want want);

    Want select(int sellID, int idx);

    List<Want> selectAll(int idx, Paging paging);

    void delete(int sellID, int idx);

    int count(int idx);
}
