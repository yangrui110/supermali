package com.supermali.creater;

import com.supermali.behavior.Gravity;
import com.supermali.entity.MapImageAbstract;
import com.supermali.entity.npc.person.Person;
import com.supermali.listener.KeyEventSupport;
import com.supermali.listener.MouseSupport;

import javax.swing.*;
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

    private JFrame frame ;

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
            if(select.getJumpBehavior().isOver())
                select.getJumpBehavior().jump();
        } else if(KeyEventSupport.getPressed(KeyEvent.VK_RIGHT)!=0){
            select.getForwordBehavior().forward(delta);
            double x = select.getX();
            if(x>500){
                creater.moveForworld();
            }
        }else if(KeyEventSupport.getPressed(KeyEvent.VK_LEFT)!=0){
            select.getGobackBehavior().goback(delta);
        }else if(KeyEventSupport.getPressed(KeyEvent.VK_DOWN)!=0){
            select.getSquatDownBehavior().squat();
        }else {
            select.getTerminateBehavior().terminate();
        }

        // 必须保证人物有支撑点
        Gravity.ensureFactorInFloor(select);
        List<? extends MapImageAbstract> hinderMap = creater.getHinderMap();
        select.setHinders(hinderMap);
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public void proccessMouse(){
        if(MouseSupport.isMouseClick()){
            MouseSupport.setMouseClick(false);
            frame.requestFocus();
        }
    }
}
