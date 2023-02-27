package com.project.shop.feature.parcel.service;

import com.project.shop.feature.page.Paging;
import com.project.shop.feature.parcel.dto.PostAddParcel;
import com.project.shop.feature.parcel.entity.Parcel;
import com.project.shop.feature.purchase.dto.PostDoPay;
import com.project.shop.feature.purchase.entity.Purchase;

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

    List<Parcel> select(int idx, Paging paging);

    void deleteByPurchaseID(int purchaseID);

    void deleteByParcelID(int parcelID);

    void updateStatus(int status, int parcelID);
    String makeWaybillNumber();

    int count(int idx);

    PostAddParcel add(PostDoPay postDoPay, Purchase purchase, String waybillNumber, int purchaseID);
}
