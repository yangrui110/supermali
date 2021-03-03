package com.supermali.entity.map.hinder.compose;

import com.supermali.creater.MapCreater;
import com.supermali.entity.map.hinder.Floor;
import com.supermali.entity.map.hinder.HinderMapAbstract;
import com.supermali.entity.map.hinder.Question;
import com.supermali.entity.npc.monistor.GrowthMushroom;
import com.supermali.entity.npc.monistor.MonistorAbstract;

import java.util.List;

/**
 * 问号和长大蘑菇的组合
 * */
public class QuestionGrowthMushroom extends Question {

    public QuestionGrowthMushroom(Double x, Double y, MapCreater mapCreater) {
        super(x, y, mapCreater);
    }

    @Override
    public void proccessData(long delta) {
//        super.proccessData(delta);
    }

    /**
     * 销毁方法，顶出生长蘑菇或者
     * */
    @Override
    public void destroy(long delta) {
        List<HinderMapAbstract> hinderMap = getMapCreater().getHinderMapAbstracts();
        hinderMap.remove(this);
        GrowthMushroom mushroom = new GrowthMushroom(getX(), getY(), getMapCreater());
        List<MonistorAbstract> monistorAbstracts = getMapCreater().getMonistorAbstracts();
        monistorAbstracts.add(mushroom);
        Floor floor = new Floor(getX(), getY(), getMapCreater());
        hinderMap.add(floor);
    }
}
