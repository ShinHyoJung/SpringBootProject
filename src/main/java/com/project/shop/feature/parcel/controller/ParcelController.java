package com.project.shop.feature.parcel.controller;

import com.project.shop.feature.parcel.entity.Parcel;
import com.project.shop.feature.parcel.service.ParcelService;
import com.project.shop.feature.util.DateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
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
@Controller
@RequiredArgsConstructor
@RequestMapping("/parcel")
public class ParcelController {

    private final static String VIEW_PREFIX = "parcel/";
    private final ParcelService parcelService;
    @GetMapping("/delivery-tracking")
    public String getDeliveryTracking(Model model, HttpSession session) {
        List<Parcel> parcelList = new ArrayList<>();
        try {
            int idx = (int)session.getAttribute("idx");
            parcelList = parcelService.select(idx);

            for(Parcel parcel : parcelList) {
                Date purchaseDate = parcel.getPurchaseDate();
                long diffDays = DateUtils.getDayDifference(purchaseDate, new Date());

                if(diffDays == 0) {
                    parcel.setStatus(0);
                    parcelService.updateStatus(0, parcel.getParcelID());
                } else if(diffDays == 1) {
                    parcel.setStatus(1);
                    parcelService.updateStatus(1, parcel.getParcelID());
                } else if(diffDays == 2) {
                    parcel.setStatus(2);
                    parcelService.updateStatus(2, parcel.getParcelID());
                } else if(diffDays == 3) {
                    parcel.setStatus(3);
                    parcelService.updateStatus(3, parcel.getParcelID());
                } else {
                    parcelService.deleteByParcelID(parcel.getParcelID());
                }
            }
        } catch (Exception e) {
            parcelList = null;
        }
        model.addAttribute("parcelList", parcelList);
        model.addAttribute("menu", "user");
        model.addAttribute("main", VIEW_PREFIX + "deliveryTracking");
        return "view";
    }
}
