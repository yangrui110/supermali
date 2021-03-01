package com.supermali.entity.npc;

import com.supermali.entity.MapImageAbstract;

import java.util.List;

/**
 * @project super-mali
 * @Date 2021/2/28
 * @Auth yangrui
 **/
public abstract class NPCAbstract extends MapImageAbstract {


    public NPCAbstract() {
        super();
    }

    public NPCAbstract(int x, int y) {
        super(x, y);
    }

    /**
     * 检测碰撞
     * */
    public abstract boolean checkCollide(List<? extends MapImageAbstract> npcAbstractList);
    /**
     * 切换状态
     * */
    public abstract void changeState();
    /**
     * 跳跃
     * */
    public abstract void jump();
    /**
     * 向前移动
     * */
    public abstract void moveForward(long delta);
    /**
     * 向下移动
     * */
    public abstract void down();
    /**
     * 向后移动
     * */
    public abstract void goback();
    /**
     * 停止移动
     * */
    public abstract void terminateMove();
}
