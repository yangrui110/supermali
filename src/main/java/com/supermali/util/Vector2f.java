package com.supermali.util;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.geom.Point2D;

/**
 * @project super-mali
 * @Date 2021/2/27
 * @Auth yangrui
 **/
@Data
@NoArgsConstructor
public class Vector2f {

    private double x;

    private double y;

    public static final double EPSILON = 0.0000001;

    public Vector2f(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static Vector2f getVector2f(Point2D start, Point2D end){
        Vector2f vector2f = new Vector2f();
        double x1 = end.getX()-start.getX();
        double y1 = end.getY()-start.getY();
        x1 = getZero(x1);
        y1 = getZero(y1);
        vector2f.setX(x1);
        vector2f.setY(y1);
        return vector2f;
    }
    /**
     * 乘法
     * */
    public Vector2f mul(double scale){
        double vx = this.x * scale;
        double vy = this.y * scale;
        Vector2f f = new Vector2f();
        f.setX(vx);
        f.setY(vy);
        return f;
    }

    /**
     * 点积
     * */
    public double dot(Vector2f vector2f){
        return x*vector2f.getX()+y*vector2f.getY();
    }

    /**
     * 加法
     * */
    public Vector2f add(Vector2f vector2f){
        double vx = this.x + vector2f.getX();
        double vy = this.y + vector2f.getY();
        vx = getZero(vx);
        vy = getZero(vy);
        Vector2f f = new Vector2f();
        f.setX(vx);
        f.setY(vy);
        return f;
    }

    /**
     * 减法
     * */
    public Vector2f sub(Vector2f vector2f){
        double vx = this.x - vector2f.getX();
        double vy = this.y - vector2f.getY();
        vx = getZero(vx);
        vy = getZero(vy);
        Vector2f f = new Vector2f();
        f.setX(vx);
        f.setY(vy);
        return f;
    }

    private static double getZero(double data){
        if(Math.abs(data)<EPSILON)
            return 0;
        return data;
    }

    /**
     * 长度
     * */
    public double len(){
        return Math.sqrt(x*x+y*y);
    }
    /**
     * 缩小
     * */
    public Vector2f div(double scale){
        return new Vector2f(this.x/scale,this.y/scale);
    }
    /**
     * 正规化
     * */
    public Vector2f norm(){
        return div(len());
    }
    /**
     * 垂直
     * */
    public Vector2f perp(){
        return new Vector2f(-y,x);
    }

}
