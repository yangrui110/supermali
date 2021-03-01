package com.supermali.behavior.terminate.land;

import com.supermali.behavior.Gravity;
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
        Enum key = ImgKey.Land.TERMINATE_RIGHT;
        if(person.isLeft()){
            key = ImgKey.Land.TERMINATE_LEFT;
        }
        ImgHelper imgHelper = ImgLoader.getImgHelper(key);
        BufferedImage select = imgHelper.select(0);
        person.setBufferedImage(select);

        // 默认检测是否有支撑
        Gravity.ensureFactorInFloor(person);

    }
}
