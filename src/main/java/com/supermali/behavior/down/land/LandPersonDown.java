package com.supermali.behavior.down.land;

import com.supermali.behavior.Gravity;
import com.supermali.behavior.down.DownBehavior;
import com.supermali.creater.img.ImgHelper;
import com.supermali.creater.img.ImgKey;
import com.supermali.creater.img.ImgLoader;
import com.supermali.entity.npc.person.Person;
import com.supermali.util.ImgConvertUtil;

import java.awt.image.BufferedImage;

public class LandPersonDown extends DownBehavior {

    private boolean isOver;

    private Person person;
    // 重力: 控制下落速度
    private Gravity gravity;
    // 重力下落总时间
    private double totalTime;

    public LandPersonDown(Person person) {
        this.person = person;
        gravity = new Gravity();
        totalTime = 0;
        isOver =true;
    }

    /**
     * 下落未结束，始终保持下落状态
     * */
    @Override
    public void down() {
        isOver= false;
        totalTime+=Gravity.t;
        double y = this.person.getY();
        double dy = -0.5*Gravity.g*Gravity.t*totalTime;
        y=y+dy;
        person.setY(y);
        // 检测是否具有碰撞
        boolean collide = person.checkCollide();
        if(collide){
            isOver = true;
            totalTime = 0;
        }
        ImgHelper imgHelper = ImgLoader.getImgHelper(ImgKey.Land.TERMINATE);
        BufferedImage bufferedImage = imgHelper.select(0);
        if(person.isLeft()) {
            bufferedImage = ImgConvertUtil.mirror(bufferedImage);
        }
        person.setBufferedImage(bufferedImage);
    }

    @Override
    public boolean isOver() {
        return isOver;
    }
}
