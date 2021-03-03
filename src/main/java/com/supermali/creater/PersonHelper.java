package com.supermali.creater;

import com.supermali.entity.npc.person.Person;

/**
 * @project super-mali
 * @Date 2021/2/28
 * @Auth yangrui
 * 帮助选择到底需要哪个人物
 **/
public class PersonHelper {

    Person first;
    Person two;
    private int type;

    public PersonHelper(MapCreater mapCreater) {
        this.first = new Person(0d,32d,mapCreater);
        this.two = new Person(0d,32d,mapCreater);
        type =0;
    }

    public Person select(){
        if(type ==0) return first;
        else return two;
    }
}
