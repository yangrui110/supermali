package com.supermali.creater;

import com.supermali.creater.img.ImgLoader;
import com.supermali.entity.map.background.BackGroundMapAbstract;
import com.supermali.entity.map.hinder.HinderMapAbstract;
import com.supermali.entity.npc.monistor.MonistorAbstract;
import com.supermali.entity.npc.person.Person;
import com.supermali.xml.EntityDom;
import com.supermali.xml.XmlExplain;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @project super-mali
 * @Date 2021/2/27
 * @Auth yangrui
 **/
public class MapCreater {

    // 静态阻碍物
    private List<HinderMapAbstract> hinderMapAbstracts;
    // 动态怪物
    private List<MonistorAbstract> monistorAbstracts;
    // 背景
    private List<BackGroundMapAbstract> backGroundMapAbstracts;
    // xml解析器
    private XmlExplain xmlExplain;
    // 绝对地址
    private double absoluteWidth;
    // 人物元素
    private Person person;

    ImgLoader imgLoader;

    public MapCreater() {

        absoluteWidth = 0;
        // 加载地图元素的图片
        imgLoader = new ImgLoader();
        imgLoader.loadMapFactor();
        xmlExplain = new XmlExplain();
        hinderMapAbstracts = new ArrayList<>();
        monistorAbstracts = new ArrayList<>();
        backGroundMapAbstracts = new ArrayList<>();
        // 图片加载完成后，初始化背景
        createBackground();
    }

    /**
     * 创建背景 采用50*40
     * */
    public void createBackground(){
        xmlExplain.explain("/map.xml/Fisrt.xml");
        List<EntityDom> result = xmlExplain.getResult();
        for(EntityDom dom: result){
            // 根据形状，处理
            if(dom.getShape() == EntityDom.DrawShape.NORMAL){
                int x = dom.getX();
                int y = dom.getY();
                int startx = dom.getCx()>0?0:dom.getCx();
                int starty = dom.getCy() > 0 ? 0 : dom.getCy();
                int endx = dom.getCx()>0?dom.getCx():0;
                int endy = dom.getCy()>0?dom.getCy():0;
                for(int i=startx;i<endx;i++){
                    for(int j=starty;j<endy;j++){
                        Object instance = dom.createInstance((x+i) * 16, (y+j) * 16,this);
                        if(instance instanceof HinderMapAbstract){
                            hinderMapAbstracts.add((HinderMapAbstract) instance);
                        }else if(instance instanceof MonistorAbstract){
                            monistorAbstracts.add((MonistorAbstract) instance);
                        }else if(instance instanceof BackGroundMapAbstract){
                            backGroundMapAbstracts.add((BackGroundMapAbstract) instance);
                        }
                    }
                }
            }
        }
    }

    public void show(Graphics graphics){
        for(BackGroundMapAbstract backGroundMapAbstract: backGroundMapAbstracts){
            backGroundMapAbstract.make(graphics);
        }
        for(HinderMapAbstract backGroundMapAbstract: hinderMapAbstracts){
            backGroundMapAbstract.make(graphics);
        }
        for(MonistorAbstract monistorAbstract: monistorAbstracts){
            monistorAbstract.make(graphics);
        }
    }

    public void proccessData(long delta){
        for(BackGroundMapAbstract backGroundMapAbstract: backGroundMapAbstracts){
            backGroundMapAbstract.proccessData(delta);
        }
        for(HinderMapAbstract backGroundMapAbstract: hinderMapAbstracts){
            backGroundMapAbstract.proccessData(delta);
        }
        for(MonistorAbstract monistorAbstract: monistorAbstracts){
            monistorAbstract.proccessData(delta);
        }
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    // 移动
    public void moveForworld(){
        this.absoluteWidth ++;
    }

    public List<HinderMapAbstract> getHinderMapAbstracts() {
        return hinderMapAbstracts;
    }

    public List<MonistorAbstract> getMonistorAbstracts() {
        return monistorAbstracts;
    }

    public List<BackGroundMapAbstract> getBackGroundMapAbstracts() {
        return backGroundMapAbstracts;
    }
}
