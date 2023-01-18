package com.project.shop.feature.purchase.dto;

import com.project.shop.feature.purchase.entity.Purchase;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PostDoPay {
    private String name;
    private String price;
    private int quantity;
    private String address;
    private int sellID;
    private int idx;
    private String impUid;

    public List<Purchase> toEntity() {
        List<Purchase> purchaseList = new ArrayList<>();
        Purchase purchase = new Purchase();
        purchase.setName(this.name);
        purchase.setPrice(this.price);
        purchase.setQuantity(this.quantity);
        purchase.setAddress(this.address);
        purchase.setImpUid(this.impUid);
        purchase.setSellID(this.sellID);
        purchase.setIdx(this.idx);
        purchase.setOrderStatus("paymentComplete");
        purchaseList.add(purchase);
        return purchaseList;
    }
}
