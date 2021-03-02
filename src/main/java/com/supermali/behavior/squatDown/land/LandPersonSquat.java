package com.supermali.behavior.squatDown.land;

import com.supermali.behavior.squatDown.SquatDownBehavior;
import com.supermali.entity.npc.person.Person;

/**
 * 人物下蹲
 * */
public class LandPersonSquat extends SquatDownBehavior {

    Person person;

    public LandPersonSquat(Person person) {
        this.person = person;
    }
    /**
     * 蹲下去
     * */
    @Override
    public void squat() {

    }


}
