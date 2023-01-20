package com.project.shop.feature.parcel.dto;

import com.project.shop.feature.page.Paging;
import com.project.shop.feature.parcel.entity.Parcel;
import lombok.Data;

import java.util.List;

/**
 * <pre>
 * Created with IntelliJ IDEA.
 * Company: NANDSOFT
 * User: sljh1020
 * Date: 2023-01-19
 * Comments:
 * </pre>
 */
@Data
public class PostDeliveryTrackingListResponse {
    private List<Parcel> parcelList;
    private Paging paging;
}
