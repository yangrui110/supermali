package com.supermali.behavior.jump.land;

import com.supermali.behavior.Gravity;
import com.supermali.behavior.jump.JumpBehavior;
import com.supermali.creater.img.ImgHelper;
import com.supermali.creater.img.ImgKey;
import com.supermali.creater.img.ImgLoader;
import com.supermali.entity.npc.person.Person;

import java.awt.image.BufferedImage;

public class LandPersonJump extends JumpBehavior {

    private Person person;

    private boolean isOver; // 判断跳跃过程是否结束

    private double theta;

    public LandPersonJump(Person person) {
        this.person = person;
        isOver = true;
        theta = 0;
    }

    @Override
    public void jump() {
        isOver= false;
        double t = Gravity.t;
        double g = Gravity.g;
        double v = 60;
        theta+= t;
        double dy = v*t - g*t*theta;
        double vy = person.getY() + dy;
        person.setY(vy);
        // 碰到障碍物，结束跳跃
        boolean checkCollide = person.checkCollide();
        if(checkCollide){
            isOver = true;
            theta = 0;
        }
        ImgHelper imgHelper = ImgLoader.getImgHelper(ImgKey.Land.JUMP);
        BufferedImage bufferedImage = imgHelper.select(0);
        person.setBufferedImage(bufferedImage);
    }

    public boolean isOver() {
        return isOver;
    }
}
