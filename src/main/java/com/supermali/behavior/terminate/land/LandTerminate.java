package com.supermali.behavior.terminate.land;

import com.supermali.behavior.terminate.TerminateBehavior;
import com.supermali.creater.img.ImgHelper;
import com.supermali.creater.img.ImgKey;
import com.supermali.creater.img.ImgLoader;
import com.supermali.entity.npc.person.Person;
import com.supermali.util.ImgConvertUtil;

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
        if(person.isLeft()){
            select = ImgConvertUtil.mirror(select);
        }
        person.setBufferedImage(select);

    }
}
