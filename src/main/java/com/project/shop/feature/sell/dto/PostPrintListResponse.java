package com.project.shop.feature.sell.dto;

import com.project.shop.feature.page.Paging;
import com.project.shop.feature.sell.entity.Sell;
import lombok.Data;

import java.util.List;

/**
 * <pre>
 * Created with IntelliJ IDEA.
 * Company: NANDSOFT
 * User: sljh1020
 * Date: 2023-01-11
 * Comments:
 * </pre>
 */
@Data
public class PostPrintListResponse {
    private Paging paging;
    private List<Sell> sellList;
    public PostPrintListResponse(Paging paging, List<Sell> sellList) {
        this.paging = paging;
        this.sellList = sellList;
    }

}
