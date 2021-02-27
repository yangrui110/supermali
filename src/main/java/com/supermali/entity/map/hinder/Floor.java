package com.supermali.entity.map.hinder;

/**
 * @project super-mali
 * @Date 2021/2/28
 * @Auth yangrui
 **/
public class Floor extends HinderMapAbstract {
    public Floor() {
    }

    public Floor(int x, int y) {
        super(x, y);
    }

    @Override
    public void init() {
        this.loadImg("/img/map/hinder/floor.png");
    }
}
