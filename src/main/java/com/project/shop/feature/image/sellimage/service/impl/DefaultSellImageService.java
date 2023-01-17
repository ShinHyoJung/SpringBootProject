package com.project.shop.feature.image.sellimage.service.impl;

import com.project.shop.feature.image.sellimage.dao.SellImageDAO;
import com.project.shop.feature.image.sellimage.entity.SellImage;
import com.project.shop.feature.image.sellimage.service.SellImageService;
import lombok.RequiredArgsConstructor;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.name.Rename;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


@Service("SellImageService")
@RequiredArgsConstructor
public class DefaultSellImageService implements SellImageService {

    private final SellImageDAO sellImageDAO;

    @Override
    public void insert(List<SellImage> sellImageList) throws SQLException {
        sellImageDAO.insert(sellImageList);
    }

    @Override
    public List<SellImage> select(int sellID) throws SQLException {
        return sellImageDAO.select(sellID);
    }
}
