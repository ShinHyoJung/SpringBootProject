package com.project.shop.feature.parcel.entity;

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
public class Parcel {
    private int parcelID;
    private String name;
    private String address;
    private int status;
    private String waybillNumber;
    private int purchaseID;
    private int productID;
    private int sellID;
    private int idx;
}
