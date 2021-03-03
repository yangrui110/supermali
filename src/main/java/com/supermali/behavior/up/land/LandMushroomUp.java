package com.supermali.behavior.up.land;

import com.supermali.behavior.up.UpBehavior;
import com.supermali.behavior.util.Up16Px;

public class LandMushroomUp extends UpBehavior {

    private Up16Px up16Px;

    public LandMushroomUp() {
    }

    @Override
    public void up(long delta) {
        up16Px.up(delta);
    }
    public boolean isOver() {
        return up16Px.isOver();
    }
}
