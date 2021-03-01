package com.supermali.behavior.forward.land;

import com.supermali.behavior.down.DownBehavior;
import com.supermali.behavior.forward.MoveForwordBehavior;
import com.supermali.behavior.jump.JumpBehavior;
import com.supermali.creater.img.ImgHelper;
import com.supermali.creater.img.ImgKey;
import com.supermali.creater.img.ImgLoader;
import com.supermali.entity.npc.person.Person;

import java.awt.image.BufferedImage;
import java.util.Map;

public class LandPersonMoveForword extends MoveForwordBehavior {

    private Person person;

    private long rate = 0; // 控制图片切换速度

    private int index = 0; // 显示图片的索引

    public LandPersonMoveForword(Person person) {
        this.person = person;
    }

    @Override
    public void forward(long delta) {

        // 前进
        double x = person.getX();
        if(x <500){
            x+=0.1;
            person.setX(x);
        }
        // 控制切换图片的帧率
        long l = delta / 60;

        if(l==0||l>rate) {
            // 帧率
            rate = l;
            index++;
            // 获取需要显示的图片
            Map<Enum, ImgHelper> imgs = ImgLoader.getImgs();
            ImgHelper imgHelper = imgs.get(ImgKey.Land.FORWARD);
            BufferedImage select = imgHelper.select(index);
            person.setBufferedImage(select);
        }
        JumpBehavior jumpBehavior = person.getJumpBehavior();
        DownBehavior downBehavior = person.getDownBehavior();
        // 还在跳跃过程,继续跳跃
        if(!jumpBehavior.isOver()){
            jumpBehavior.jump();
        }
        // 还在下降过程，继续下降
        if(!downBehavior.isOver()){
            downBehavior.down();
        }
    }
}
