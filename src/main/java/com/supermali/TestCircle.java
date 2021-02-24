package com.supermali;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;

/**
 * @project super-mali
 * @Date 2021/2/24
 * @Auth yangrui
 **/
public class TestCircle {

    private double theta;

    Point2D point2D ;

    Point2D orign; // 将要移动到的原点
    public TestCircle() {
        this.theta = 0;
        point2D = new Point2D.Double(0,0);
        orign = new Point2D.Double(200,200);
    }

    public void drawCircle(Graphics graphics){
        theta+= 0.001;
        // 1、构建平移和旋转
        AffineTransform finalTrans = new AffineTransform();
        finalTrans.setToIdentity();

        AffineTransform affineTransform = new AffineTransform();
        affineTransform.translate(50,50);
        Point2D d = new Point2D.Double();
        finalTrans.concatenate(affineTransform);
//        affineTransform.transform(this.point2D, d);
        AffineTransform rotate = new AffineTransform();
        rotate.setToIdentity();
        rotate.rotate(theta);
        finalTrans.concatenate(rotate);

//        finalTrans.createTransformedShape(this.point2D);
//        rotate.transform(d,d);

        // 2、旋转

//        System.out.println("d:x="+d.getX()+" y="+ d.getY());
        graphics.drawArc((int)d.getX(),(int)d.getY(),10,10,0,360);
    }

    // 自定义坐标系，转换

}
