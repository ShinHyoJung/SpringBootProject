package com.project.shop.feature.purchase.dto;

import com.project.shop.feature.purchase.entity.Purchase;
import lombok.Data;

@Data
public class PostDoPay {
    private String name;
    private String price;
    private String address;
    private int sellID;
    private int idx;

    public Purchase toEntity() {
        Purchase purchase = new Purchase();
        purchase.setName(this.name);
        purchase.setPrice(this.price);
        purchase.setAddress(this.address);
        purchase.setSellID(this.sellID);
        purchase.setIdx(this.idx);
        purchase.setDeliveryStatus("paymentComplete");
        return purchase;
    }
}
