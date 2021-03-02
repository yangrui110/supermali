package com.supermali.entity.map.hinder;

import com.supermali.creater.img.ImgHelper;
import com.supermali.creater.img.ImgKey;
import com.supermali.creater.img.ImgLoader;

import java.awt.image.BufferedImage;

/**
 * @project super-mali
 * @Date 2021/2/28
 * @Auth yangrui
 **/
public class Floor extends HinderMapAbstract {
    public Floor() {
        super();
    }

    public Floor(Double x, Double y) {
        super(x, y);
    }

    @Override
    public void init() {
        ImgHelper imgHelper = ImgLoader.getImgHelper(ImgKey.Land.FLOOR);
        BufferedImage select = imgHelper.select(0);
        this.setBufferedImage(select);
    }
}
