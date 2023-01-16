package com.project.shop.feature.sell.dto;

import com.project.shop.feature.sell.entity.Sell;
import lombok.Data;
import lombok.NonNull;

@Data
public class GetDetailResponse {
    @NonNull
    private Sell sell;
}
