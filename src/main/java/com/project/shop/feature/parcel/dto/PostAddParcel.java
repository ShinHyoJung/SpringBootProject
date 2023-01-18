package com.project.shop.feature.parcel.dto;

import com.project.shop.feature.parcel.entity.Parcel;
import lombok.Data;

import java.util.Date;

/**
 * <pre>
 * Created with IntelliJ IDEA.
 * Company: NANDSOFT
 * User: sljh1020
 * Date: 2023-01-18
 * Comments:
 * </pre>
 */
@Data
public class PostAddParcel {
    private String name;
    private String address;
    private int quantity;
    private int status;
    private String waybillNumber;
    private int purchaseID;
    private int productID;
    private int sellID;
    private int idx;
    private Date purchaseDate;

    public Parcel toEntity() {
        Parcel parcel = new Parcel();
        parcel.setName(this.name);
        parcel.setAddress(this.address);
        parcel.setQuantity(this.quantity);
        parcel.setStatus(this.status);
        parcel.setWaybillNumber(this.waybillNumber);
        parcel.setPurchaseID(this.purchaseID);
        parcel.setProductID(this.productID);
        parcel.setSellID(this.sellID);
        parcel.setIdx(this.idx);
        parcel.setPurchaseDate(this.purchaseDate);
        return parcel;
    }
}
