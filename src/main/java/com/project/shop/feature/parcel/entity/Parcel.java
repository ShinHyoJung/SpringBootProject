package com.project.shop.feature.parcel.entity;

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
public class Parcel {
    private int parcelID;
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
    private Date shipDate;
}
