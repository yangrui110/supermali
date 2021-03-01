package com.supermali.entity.npc;

import com.supermali.entity.MapImageAbstract;

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
    public abstract boolean checkCollide();
}
