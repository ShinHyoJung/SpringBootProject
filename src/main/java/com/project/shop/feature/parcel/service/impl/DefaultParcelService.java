package com.project.shop.feature.parcel.service.impl;

import com.project.shop.feature.parcel.dao.ParcelDAO;
import com.project.shop.feature.parcel.entity.Parcel;
import com.project.shop.feature.parcel.service.ParcelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
@Service("ParcelService")
@RequiredArgsConstructor
public class DefaultParcelService implements ParcelService {

    private final ParcelDAO parcelDAO;

    @Override
    public void insert(Parcel parcel) {
        parcelDAO.insert(parcel);
    }

    @Override
    public List<Parcel> select(int idx) {
        return parcelDAO.select(idx);
    }
}
