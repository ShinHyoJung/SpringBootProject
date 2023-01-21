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
    private String detailAddress;
    private String zipCode;
    private String impUid;
    private int sellID;
    private int productID;
    private int idx;

    public List<Purchase> toEntity() {
        List<Purchase> purchaseList = new ArrayList<>();
        Purchase purchase = new Purchase();
        purchase.setName(this.name);
        purchase.setPrice(this.price);
        purchase.setQuantity(this.quantity);
        purchase.setAddress(this.address);
        purchase.setDetailAddress(this.detailAddress);
        purchase.setZipCode(this.zipCode);
        purchase.setImpUid(this.impUid);
        purchase.setSellID(this.sellID);
        purchase.setProductID(this.productID);
        purchase.setIdx(this.idx);
        purchase.setOrderStatus("paymentComplete");
        purchaseList.add(purchase);
        return purchaseList;
    }
}
