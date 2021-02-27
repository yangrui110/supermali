package com.supermali.creater;

import com.supermali.entity.map.background.BackGroundMapAbstract;
import com.supermali.entity.map.background.FloorDown;
import com.supermali.entity.map.background.Sky;
import com.supermali.entity.map.hinder.Floor;
import com.supermali.entity.map.hinder.HinderMapAbstract;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @project super-mali
 * @Date 2021/2/27
 * @Auth yangrui
 **/
public class MapCreater {

    // 蓝色天空
    private List<Sky> blueSkys ;
    // 地板
    private List<Floor> floor;
    // 水下
    private List<FloorDown> floorDowns;
    public MapCreater() {
        createBackground();
    }

    /**
     * 创建背景 采用50*40
     * */
    public void createBackground(){
        // 蓝色天空
        ArrayList<Sky> list = new ArrayList<>();
        for(int i=0;i<50;i++){
            for(int j=0;j<40;j++){
                Sky sky = new Sky(i*16,j*16);
                list.add(sky);
            }
        }
        this.blueSkys = list;
        // 地板
        List<Floor> floorList = new ArrayList<>();
        for(int i=0;i<50;i++ ){
            for(int j=0;j<2;j++){
                Floor floor = new Floor(i*16,j*16);
                floorList.add(floor);
            }
        }
        this.floor = floorList;
        // 地下
        ArrayList<FloorDown> floorDowns = new ArrayList<>();
        for(int i=0;i<50;i++ ){
            for(int j=0;j<40;j++){
                FloorDown floorDown = new FloorDown(i*16,-j*16);
                floorDowns.add(floorDown);
            }
        }
        this.floorDowns = floorDowns;
    }

    public void show(Graphics graphics){
        for(BackGroundMapAbstract backGroundMapAbstract: blueSkys){
            backGroundMapAbstract.make(graphics);
        }
        for(BackGroundMapAbstract backGroundMapAbstract: floorDowns){
            backGroundMapAbstract.make(graphics);
        }
        for(HinderMapAbstract backGroundMapAbstract: floor){
            backGroundMapAbstract.make(graphics);
        }
    }

}
