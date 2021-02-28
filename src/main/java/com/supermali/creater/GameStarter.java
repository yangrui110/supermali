package com.supermali.creater;

import com.supermali.KeyEventSupport;
import com.supermali.entity.npc.person.PersonAbstract;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * @project super-mali
 * @Date 2021/2/27
 * @Auth yangrui
 **/
public class GameStarter {

    private MapCreater creater;

    private PersonHelper personHelper;

    public GameStarter() {
        this.init();
    }

    public void init(){
        creater = new MapCreater();
        this.personHelper = new PersonHelper();
    }

    public void show(Graphics graphics){
        creater.show(graphics);
        PersonAbstract select = personHelper.select();
        select.make(graphics);
    }
    // 处理键盘事件
    public void proccessKey(long delta){
        PersonAbstract select = personHelper.select();
        if(KeyEventSupport.getPressed(KeyEvent.VK_UP)!=0){
            select.jump();
        } else if(KeyEventSupport.getPressed(KeyEvent.VK_RIGHT)!=0){
            select.moveForward(delta);
        }else {
            select.terminateMove();
        }
    }
}
