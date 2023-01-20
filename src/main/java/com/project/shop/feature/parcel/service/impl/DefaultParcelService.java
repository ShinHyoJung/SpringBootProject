package com.project.shop.feature.parcel.service.impl;

import com.project.shop.feature.page.Paging;
import com.project.shop.feature.parcel.dao.ParcelDAO;
import com.project.shop.feature.parcel.entity.Parcel;
import com.project.shop.feature.parcel.service.ParcelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

/**
 * <pre>
 * Created with IntelliJ IDEA.
 * Company: NANDSOFT
 * User: sljh1020
 * Date: 2023-01-18
 * Comments:
 * </pre>
 */
@Service("ParcelService")
@RequiredArgsConstructor
public class DefaultParcelService implements ParcelService {

    private final ParcelDAO parcelDAO;

    @Override
    public void insert(Parcel parcel) {
        parcelDAO.insert(parcel);
    }

    @Override
    public List<Parcel> select(int idx, Paging paging) {
        return parcelDAO.select(idx, paging);
    }

    @Override
    public void deleteByPurchaseID(int purchaseID) {
        parcelDAO.deleteByPurchaseID(purchaseID);
    }

    @Override
    public void deleteByParcelID(int parcelID) {
        parcelDAO.deleteByParcelID(parcelID);
    }

    @Override
    public void updateStatus(int status, int parcelID) {
        parcelDAO.updateStatus(status, parcelID);
    }

    public String makeWaybillNumber() {
        Random rand = new Random();
        StringBuilder numStr = new StringBuilder();
        for(int i = 0; i < 13; i++) {
            String ran = Integer.toString(rand.nextInt(10));
            numStr.append(ran);
        }
        String code = numStr.toString();
        return code;
    }

    @Override
    public int count(int idx) {
        return parcelDAO.count(idx);
    }
}
