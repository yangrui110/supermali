package com.supermali.behavior.goback.land;

import com.supermali.behavior.Gravity;
import com.supermali.behavior.goback.GobackBehavior;
import com.supermali.creater.img.ImgHelper;
import com.supermali.creater.img.ImgKey;
import com.supermali.creater.img.ImgLoader;
import com.supermali.entity.npc.person.Person;

import java.awt.image.BufferedImage;
import java.util.Map;

public class LandPersonGoBack extends GobackBehavior {

    private Person person;

    private long rate = 0; // 控制图片切换速度

    private int index = 0; // 显示图片的索引

    public LandPersonGoBack(Person person) {
        this.person = person;
    }

    @Override
    public void goback(long delta) {
        person.setDirect(Person.Direct.Left);
        // 前进
        double x = person.getX();
        if(x >0){
            x-=0.1;
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
            ImgHelper imgHelper = imgs.get(ImgKey.Land.GOBACK);
            BufferedImage select = imgHelper.select(index);
            person.setBufferedImage(select);
        }
        Gravity.ensureFactorInFloor(person);
    }
}
