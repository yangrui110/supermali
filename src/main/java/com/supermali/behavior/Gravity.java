package com.supermali.behavior;

import com.supermali.behavior.down.DownBehavior;
import com.supermali.behavior.jump.JumpBehavior;
import com.supermali.entity.npc.person.Person;

public class Gravity {

    // 间隔时间
    public static final double t= 0.06;
    // 加速度
    public static final double g =9.8;

    /**
     * 确保人物在支撑物上
     * */
    public static void ensureFactorInFloor(Person person){
        JumpBehavior jumpBehavior = person.getJumpBehavior();
        DownBehavior downBehavior = person.getDownBehavior();
        // 还在跳跃过程,继续跳跃
        if(!jumpBehavior.isOver()){
            jumpBehavior.jump();
        }
        // 还在下降过程，继续下降
        else if(!downBehavior.isOver()){
            downBehavior.down();
        }else {
            boolean collide = person.checkCollide();
            if(!collide){
                downBehavior.down();
            }
        }
    }
}
