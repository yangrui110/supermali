package com.supermali.entity.map.background;

import com.supermali.creater.img.ImgHelper;
import com.supermali.creater.img.ImgKey;
import com.supermali.creater.img.ImgLoader;

import java.awt.image.BufferedImage;

/**
 * @project super-mali
 * @Date 2021/2/27
 * @Auth yangrui
 **/
public class Sky extends BackGroundMapAbstract {
    public Sky() {
        super();
    }

    public Sky(int x, int y) {
        super(x,y);
    }

    // 初始化，设置图片属性
    @Override
    public void init() {
        ImgHelper imgHelper = ImgLoader.getImgHelper(ImgKey.Land.SKY);
        BufferedImage select = imgHelper.select(0);
        this.setBufferedImage(select);
    }
}
