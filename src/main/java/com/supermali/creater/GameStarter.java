package com.supermali.creater;

import com.supermali.KeyEventSupport;
import com.supermali.behavior.down.DownBehavior;
import com.supermali.behavior.jump.JumpBehavior;
import com.supermali.entity.MapImageAbstract;
import com.supermali.entity.npc.person.Person;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;

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
        Person select = personHelper.select();
        select.make(graphics);
    }
    // 处理键盘事件
    public void proccessKey(long delta){
        Person select = personHelper.select();

        if(KeyEventSupport.getPressed(KeyEvent.VK_UP)!=0){
            select.getJumpBehavior().jump();
        } else if(KeyEventSupport.getPressed(KeyEvent.VK_RIGHT)!=0){
            select.getForwordBehavior().forward(delta);
            double x = select.getX();
            if(x>500){
                creater.moveForworld();
            }
        }else {
            select.getTerminateBehavior().terminate();
        }
        List<? extends MapImageAbstract> hinderMap = creater.getHinderMap();
        select.setHinders(hinderMap);
    }
}
