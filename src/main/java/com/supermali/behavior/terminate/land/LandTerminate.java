package com.supermali.behavior.terminate.land;

import com.supermali.behavior.down.DownBehavior;
import com.supermali.behavior.jump.JumpBehavior;
import com.supermali.behavior.terminate.TerminateBehavior;
import com.supermali.creater.img.ImgHelper;
import com.supermali.creater.img.ImgKey;
import com.supermali.creater.img.ImgLoader;
import com.supermali.entity.npc.person.Person;

import java.awt.image.BufferedImage;

/**
 * @project super-mali
 * @Date 2021/3/1
 * @Auth yangrui
 **/
public class LandTerminate extends TerminateBehavior {

    private Person person;

    public LandTerminate(Person person) {
        this.person = person;
    }

    @Override
    public void terminate() {
        ImgHelper imgHelper = ImgLoader.getImgHelper(ImgKey.Land.TERMINATE);
        BufferedImage select = imgHelper.select(0);
        person.setBufferedImage(select);

        // 默认检测是否有支撑
        JumpBehavior jumpBehavior = person.getJumpBehavior();
        DownBehavior downBehavior = person.getDownBehavior();
        // 还在跳跃过程,继续跳跃
        if(!jumpBehavior.isOver()){
            jumpBehavior.jump();
        }
        // 还在下降过程，继续下降
        else if(!downBehavior.isOver()){
            downBehavior.down();
        }else {
            // 判断是否有支撑物
            boolean collide = person.checkCollide();
            if(!collide){
                downBehavior.down();
            }
        }

    }
}
