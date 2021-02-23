package com.supermali;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

/**
 * @project super-mali
 * @Date 2021/2/22
 * @Auth yangrui
 **/
public class CanvasCircle extends JPanel {

    private Circle circle ;
    private AffineTransform affineTransform;
    double v = Math.PI * 2 / 100;
    double delta = 0;

    public CanvasCircle() {
        this.affineTransform = new AffineTransform();
        this.circle = new Circle();
        affineTransform.setToTranslation(300,300);
        AffineTransform transform = new AffineTransform();
        transform.rotate(v);
        this.affineTransform.concatenate(transform);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.drawArc((int)circle.getX(),(int)circle.getY(),20,20,0,360);
        System.out.println("重新绘图：x:"+this.circle.getX()+" y:"+this.circle.getY());

        Point2D transform1 = this.affineTransform.transform(new Point2D.Double(circle.getX(), circle.getY()), null);
        System.out.println("转换后的点：x:"+transform1.getX()+" y:"+transform1.getY());

        this.circle.setX(transform1.getX());
        this.circle.setY(transform1.getY());

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(delta<Math.PI*2) {
            this.repaint();
        }

    }

    @Override
    public void update(Graphics g) {
        System.out.println("重新绘图--1：x:"+this.circle.getX()+" y:"+this.circle.getY());
        super.update(g);
//        this.repaint();
    }


}
