package com.project.shop.feature.sell.dto;

import com.project.shop.feature.page.Paging;
import com.project.shop.feature.sell.entity.Sell;
import lombok.Data;

import java.util.List;

@Data
public class GetDefaultResponse {
    private List<Sell> sellList;
    private Paging paging;

    public GetDefaultResponse(List<Sell> sellList, Paging paging) {
        this.sellList = sellList;
        this.paging = paging;
    }
}
