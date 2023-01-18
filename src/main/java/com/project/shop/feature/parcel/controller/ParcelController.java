package com.project.shop.feature.parcel.controller;

import com.project.shop.feature.parcel.entity.Parcel;
import com.project.shop.feature.parcel.service.ParcelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
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
    @GetMapping("/")
    public String getParcel(Model model, HttpSession session) {
        int idx = (int)session.getAttribute("idx");
        List<Parcel> parcelList = parcelService.select(idx);

        model.addAttribute("parcelList", parcelList);
        model.addAttribute("menu", "user");
        model.addAttribute("main", VIEW_PREFIX + "default");
        return "view";
    }
}
