package com.project.shop.feature.sell.dto;

import com.project.shop.feature.sell.entity.Sell;
import lombok.Data;
import lombok.NonNull;

import java.util.Date;

@Data
public class GetDetailResponse {
    @NonNull
    private Sell sell;
}
