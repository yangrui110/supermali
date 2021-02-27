package com.supermali.util;

import java.awt.geom.AffineTransform;

/**
 * @project super-mali
 * @Date 2021/2/27
 * @Auth yangrui
 **/
public class WorldTransform {
    public static AffineTransform getWorldAffineTransform(){
        // 先翻转
        AffineTransform worldTransform = new AffineTransform();
        worldTransform.scale(1,-1);
        // 再平移
        AffineTransform transform = new AffineTransform();
        transform.translate(0,400);
        worldTransform.preConcatenate(transform);
        return worldTransform;
    }
}
