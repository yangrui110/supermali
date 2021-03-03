package com.supermali.behavior.util;

import com.supermali.behavior.Dxy;
/**
 * 上升16像素的行为
 * */
public class Up16Px{
    // 是否结束
    private boolean isOver;

    private double MAX_TOP=16;

    // 计算总的移动距离，视为结束判断
    private double totalY;
    // 速率
    private double rate;
    public Up16Px() {
        isOver=true;
        this.totalY=0;
        this.rate = MAX_TOP/3000;
    }

    // 控制3秒钟上升完毕
    public Dxy up(long delta) {
        Dxy dxy = new Dxy();
        // 如果上一个还未结束，继续上一个
        isOver=false;
        double v = this.rate * delta/60;
        totalY+=v;
        if(totalY>MAX_TOP){
            this.isOver=true;
            this.totalY=0;
            return dxy;
        }
        dxy.setDy(v);
        return dxy;
    }

    public boolean isOver() {
        return isOver;
    }
}
