package com.supermali.entity.npc.monistor;

import com.supermali.behavior.Dxy;
import com.supermali.behavior.util.Up16Px;
import com.supermali.creater.MapCreater;
import com.supermali.creater.img.ImgHelper;
import com.supermali.creater.img.ImgKey;
import com.supermali.creater.img.ImgLoader;

import java.awt.image.BufferedImage;

public class GrowthMushroom extends MonistorAbstract {

    // 上移16px
    Up16Px up16Px;

    public GrowthMushroom(Double x, Double y, MapCreater mapCreater) {
        super(x, y, mapCreater);
    }

    @Override
    public boolean checkCollide() {
        return false;
    }

    @Override
    public void proccessData(long delta) {
        if(!up16Px.isOver()){
            Dxy dxy = up16Px.up(delta);
            this.setY(getY()+dxy.getDy());
        }else {

        }
    }

    @Override
    public void init() {
        ImgHelper imgHelper = ImgLoader.getImgHelper(ImgKey.Land.GROWTH_MUSHROOM);
        BufferedImage select = imgHelper.select(0);
        this.setBufferedImage(select);
        this.up16Px = new Up16Px();
        this.up16Px.up(0);
    }
}
