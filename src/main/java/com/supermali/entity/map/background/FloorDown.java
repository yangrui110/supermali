package com.supermali.entity.map.background;

import com.supermali.creater.img.ImgHelper;
import com.supermali.creater.img.ImgKey;
import com.supermali.creater.img.ImgLoader;

import java.awt.image.BufferedImage;

/**
 * @project super-mali
 * @Date 2021/2/28
 * @Auth yangrui
 **/
public class FloorDown extends BackGroundMapAbstract {

    public FloorDown() {
    }

    public FloorDown(int x, int y) {
        super(x, y);
    }

    @Override
    public void init() {
        ImgHelper imgHelper = ImgLoader.getImgHelper(ImgKey.Land.FLOOR_DOWN);
        BufferedImage select = imgHelper.select(0);
        this.setBufferedImage(select);
    }
}
