package com.supermali.creater;

import com.supermali.entity.npc.person.Person;
import com.supermali.entity.npc.person.PersonAbstract;

/**
 * @project super-mali
 * @Date 2021/2/28
 * @Auth yangrui
 * 帮助选择到底需要哪个人物
 **/
public class PersonHelper {

    PersonAbstract first;
    PersonAbstract two;
    private int type;

    public PersonHelper() {
        this.first = new Person(0,32);
        this.two = new Person(0,32);
        type =0;
    }

    public PersonAbstract select(){
        if(type ==0) return first;
        else return two;
    }
}
