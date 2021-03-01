package com.supermali.behavior.down.land;

import com.supermali.behavior.Gravity;
import com.supermali.behavior.down.DownBehavior;
import com.supermali.entity.npc.person.Person;

public class LandPersonDown extends DownBehavior {

    private boolean isOver;

    private Person person;
    // 重力: 控制下落速度
    private Gravity gravity;
    // 重力下落总时间
    private long totalTime;

    public LandPersonDown(Person person) {
        this.person = person;
        gravity = new Gravity();
        totalTime = 0;
        isOver =true;
    }

    /**
     * 下落未结束，始终保持下落状态
     * */
    @Override
    public void down() {
        if(!isOver){
            totalTime+=Gravity.t;
            double y = this.person.getY();
            double dy = -0.5*Gravity.g*Gravity.t*totalTime;
            y=y+dy;
            person.setY(y);
            // 检测是否具有碰撞
            boolean collide = person.checkCollide();
            if(collide){
                isOver = true;
                totalTime = 0;
            }
        }
    }
}
