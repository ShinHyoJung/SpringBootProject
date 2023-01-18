package com.project.shop.feature.purchase.dto;

import com.project.shop.feature.page.Paging;
import com.project.shop.feature.purchase.entity.Purchase;
import lombok.Data;

import java.util.List;

@Data
public class PostOrderedListResponse {
    private List<Purchase> purchaseList;
    private Paging paging;
    private String code;
    private String message;
}
