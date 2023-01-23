package com.project.shop.feature.want.dto;

import com.project.shop.feature.page.Paging;
import com.project.shop.feature.want.entity.Want;
import lombok.Data;

import java.util.List;

@Data
public class PostPrintListResponse {
    private List<Want> wantList;
    private Paging paging;
    private String code;
    private String message;
}
