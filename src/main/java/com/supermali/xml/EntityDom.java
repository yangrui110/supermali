package com.supermali.xml;

import lombok.Data;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

@Data
public class EntityDom {
    // 第x个方块开始
    private int x;
    // 第y个方块开始
    private int y;
    // x方向多少个
    private int cx;
    // y方向多少个
    private int cy;
    // 对应的实体类路径
    private String entityPath;
    // shape 形状
    private String shape;

    public enum DrawShape{
        NORMAL("normal"),
        CIRCLE("circle")
        ,RECTANGLE("rectangle")
        ,STAIRS("stairs")
        ,REVERSE_STAIRS("reverse_stairs");
        String desc;

        DrawShape(String desc) {
            this.desc = desc;
        }

        public String getDesc() {
            return desc;
        }
    }

    public DrawShape getShape() {
        if(shape==null) {
            return DrawShape.NORMAL;
        }else {
            DrawShape[] values = DrawShape.values();
            for(DrawShape drawShape: values){
                String desc = drawShape.desc;
                if(desc.equalsIgnoreCase(shape)){
                    return drawShape;
                }
            }
            return DrawShape.NORMAL;
        }
    }

    public Object createInstance(double x, double y){
        try {
            Class<?> forName = Class.forName(entityPath);
            Constructor<?> constructor = forName.getDeclaredConstructor(Double.class, Double.class);
            Object newInstance = constructor.newInstance(x, y);
            return newInstance;
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
