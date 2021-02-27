package com.supermali.util;

import java.awt.*;

/**
 * @project super-mali
 * @Date 2021/2/27
 * @Auth yangrui
 **/
public class FrameRate {

    private int rate;
    private long lastTime ;

    private String rateString;

    private long delta; // 间隔时间

    private String desc = "FPS: %s";


    public void init() {
        this.lastTime = System.currentTimeMillis();
        rateString = "";
        delta = 0;
        rate=0;
    }

    public void calculate(){
        long currentTimeMillis = System.currentTimeMillis();
        delta+=currentTimeMillis-lastTime;
        lastTime = currentTimeMillis;
        rate++;
        if(delta>1000){
            delta-=1000;
            rateString=String.format(desc,rate);
            rate=0;
        }
    }

    public void render(Graphics graphics){
        graphics.drawString(rateString,10,40);
    }

}
