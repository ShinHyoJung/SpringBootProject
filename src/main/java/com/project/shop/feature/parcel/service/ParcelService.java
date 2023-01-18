package com.project.shop.feature.parcel.service;

import com.project.shop.feature.parcel.entity.Parcel;

import java.util.List;

/**
 * <pre>
 * Created with IntelliJ IDEA.
 * Company: NANDSOFT
 * User: sljh1020
 * Date: 2023-01-18
 * Comments:
 * </pre>
 */
public interface ParcelService {

    void insert(Parcel parcel);

    List<Parcel> select(int idx);

    void deleteByPurchaseID(int purchaseID);

    void deleteByParcelID(int parcelID);

    void updateStatus(int status, int parcelID);
    String makeWaybillNumber();
}
