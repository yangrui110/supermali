package com.supermali.creater;

import java.awt.*;

/**
 * @project super-mali
 * @Date 2021/2/27
 * @Auth yangrui
 **/
public class GameStarter {

    private MapCreater creater;

    public GameStarter() {
        this.init();
    }

    public void init(){
        creater = new MapCreater();
    }

    public void show(Graphics graphics){
        creater.show(graphics);
    }
}
