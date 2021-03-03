package com.supermali.entity.map.background;

import com.supermali.creater.MapCreater;
import com.supermali.entity.MapImageAbstract;

/**
 * @project super-mali
 * @Date 2021/2/27
 * @Auth yangrui
 **/
public abstract class BackGroundMapAbstract extends MapImageAbstract {

    public BackGroundMapAbstract(Double x, Double y, MapCreater mapCreater) {
        super(x, y, mapCreater);
    }

}
