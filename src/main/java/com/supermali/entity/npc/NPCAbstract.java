package com.supermali.entity.npc;

import com.supermali.creater.MapCreater;
import com.supermali.entity.MapImageAbstract;

/**
 * @project super-mali
 * @Date 2021/2/28
 * @Auth yangrui
 **/
public abstract class NPCAbstract extends MapImageAbstract {
    public NPCAbstract(Double x, Double y, MapCreater mapCreater) {
        super(x, y, mapCreater);
    }

    /**
     * 检测碰撞
     * */
    public abstract boolean checkCollide();
}
