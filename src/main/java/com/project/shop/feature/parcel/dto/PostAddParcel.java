package com.project.shop.feature.parcel.dto;

import com.project.shop.feature.parcel.entity.Parcel;
import lombok.Data;

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
    private int status;
    private String waybillNumber;
    private int purchaseID;
    private int productID;
    private int sellID;
    private int idx;

    public Parcel toEntity() {
        Parcel parcel = new Parcel();
        parcel.setName(this.name);
        parcel.setAddress(this.address);
        parcel.setStatus(this.status);
        parcel.setWaybillNumber(this.waybillNumber);
        parcel.setPurchaseID(this.purchaseID);
        parcel.setProductID(this.productID);
        parcel.setSellID(this.sellID);
        parcel.setIdx(this.idx);
        return parcel;
    }
}
