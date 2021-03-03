package com.supermali.entity.npc.monistor;

import com.supermali.creater.MapCreater;
import com.supermali.entity.npc.NPCAbstract;

/**
 * @project super-mali
 * @Date 2021/2/28
 * @Auth yangrui
 **/
public abstract class MonistorAbstract extends NPCAbstract {
    public MonistorAbstract(Double x, Double y, MapCreater mapCreater) {
        super(x, y, mapCreater);
    }
}
