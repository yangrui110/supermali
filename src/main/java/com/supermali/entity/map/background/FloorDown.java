package com.supermali.entity.map.background;

/**
 * @project super-mali
 * @Date 2021/2/28
 * @Auth yangrui
 **/
public class FloorDown extends BackGroundMapAbstract {

    public FloorDown() {
    }

    public FloorDown(int x, int y) {
        super(x, y);
    }

    @Override
    public void init() {
        this.loadImg("/img/map/background/floorDown.png");
    }
}
