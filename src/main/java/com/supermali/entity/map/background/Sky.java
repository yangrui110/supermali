package com.supermali.entity.map.background;

/**
 * @project super-mali
 * @Date 2021/2/27
 * @Auth yangrui
 **/
public class Sky extends BackGroundMapAbstract {

    public Sky() {
    }

    public Sky(int x, int y) {
        super(x,y);
    }

    // 初始化，设置图片属性
    @Override
    public void init() {
        // 加载图片
        this.loadImg("/img/map/background/sky.png");
    }
}
