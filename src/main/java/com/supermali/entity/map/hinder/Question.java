package com.supermali.entity.map.hinder;

import com.supermali.creater.MapCreater;
import com.supermali.creater.img.ImgHelper;
import com.supermali.creater.img.ImgKey;
import com.supermali.creater.img.ImgLoader;

import java.awt.image.BufferedImage;

/**
 * @project super-mali
 * @Date 2021/2/28
 * @Auth yangrui
 **/
public class Question extends HinderMapAbstract {

    public Question(Double x, Double y, MapCreater mapCreater) {
        super(x, y, mapCreater);
    }

    @Override
    public void init() {
        ImgHelper imgHelper = ImgLoader.getImgHelper(ImgKey.Land.QUESTION);
        BufferedImage select = imgHelper.select(0);
        this.setBufferedImage(select);
    }

    /**
     * 销毁方法，默认顶出金币特效
     * */
    @Override
    public void destroy(long delta) {

    }
}
