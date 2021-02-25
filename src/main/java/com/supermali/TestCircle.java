package com.supermali;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.math.BigDecimal;

/**
 * @project super-mali
 * @Date 2021/2/24
 * @Auth yangrui
 **/
public class TestCircle {

    private double theta;

    Point2D point2D ;

    public TestCircle() {
        this.theta = 0;
        point2D = new Point2D.Double(50,50);
    }

    public void drawCircle(Graphics graphics){
        // 1、构建平移和旋转
        AffineTransform worldAffineTransform = getWorldAffineTransform();

        Point2D point2D = worldAffineTransform.transform(new Point2D.Double(0,0), null);
        Point2D endPointX = worldAffineTransform.transform(new Point2D.Double(200,0), null);
        Point2D endPointY = worldAffineTransform.transform(new Point2D.Double(0,200), null);
        // 画出用户坐标系
        int startX = (int) point2D.getX();
        int startY = (int) point2D.getY();
        double pointXX = endPointX.getX();
        double pointYY = endPointY.getY();

        graphics.drawLine(startX, startY,startX, (int) pointYY);
        graphics.drawLine(startX,startY, (int) pointXX,startY);
        graphics.drawString("y",startX, (int) pointYY);
        graphics.drawString("x", (int) pointXX,startY);

        AffineTransform rotate = new AffineTransform();
        if(KeyEventSupport.getPressed(KeyEvent.VK_RIGHT)!=0) {
            theta+=0.01;
        }
        if(KeyEventSupport.getPressed(KeyEvent.VK_LEFT)!=0) {
            theta-=0.01;
        }
        rotate.rotate(theta);
        // 获取用户坐标
        Point2D userPos = rotate.transform(this.point2D, null);
        // 转换成为世界坐标系
        rotate.preConcatenate(worldAffineTransform);
        Point2D d = rotate.transform(this.point2D, null);
        graphics.drawString("用户坐标x: "+userPos.getX()+" 用户坐标Y: "+userPos.getY(),0,20);
        graphics.drawString("世界坐标x: "+d.getX()+" 世界坐标Y: "+d.getY(),0,40);
        graphics.drawArc((int)d.getX(),(int)d.getY(),10,10,0,360);
    }

    public AffineTransform getWorldAffineTransform(){
        // 先翻转
        AffineTransform worldTransform = new AffineTransform();
        worldTransform.scale(1,-1);
        // 再平移
        AffineTransform transform = new AffineTransform();
        transform.translate(200,400);
        worldTransform.preConcatenate(transform);
        return worldTransform;
    }
}
