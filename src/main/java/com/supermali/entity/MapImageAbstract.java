package com.supermali.entity;

import com.supermali.creater.MapCreater;
import com.supermali.util.WorldTransform;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

/**
 * @project super-mali
 * @Date 2021/2/27
 * @Auth yangrui
 **/
public abstract class MapImageAbstract {
    // x坐标
    double x;
    // y坐标
    double y;
    // 图像数组
    byte[] image;

    // 地图创建器
    private MapCreater mapCreater;

    private BufferedImage bufferedImage;
    // 图像对应的shape形状
    private Shape shape;

    private AffineTransform worldAffineTransform;

    public MapImageAbstract(Double x, Double y,MapCreater mapCreater) {
        this.x = x;
        this.y = y;
        this.mapCreater=mapCreater;
        this.config();
    }

    /**
     * 生成图像
     * @param g 画笔
     * */
    public void make(Graphics g) {
        // 转换坐标到世界坐标系
        Point2D transform = worldAffineTransform.transform(new Point2D.Double(x, y), null);

        g.drawImage(bufferedImage,(int)transform.getX(),(int)transform.getY(),null);

    }
    /**
     * 在渲染图像之前处理数据
     * */
    public void proccessData(long delta){}

    /**
     * 子类复写的方法
     * */
    public abstract void init();

    /**
     * 配置
     * */
    public void config(){
        this.init();
        this.worldAffineTransform = WorldTransform.getWorldAffineTransform();
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public byte[] getImage() {
        return image;
    }

    public MapCreater getMapCreater() {
        return mapCreater;
    }

    public Shape getShape() {
        Rectangle rectangle = new Rectangle();
        rectangle.setLocation((int)x,(int)y);
        rectangle.width = bufferedImage.getWidth();
        rectangle.height = bufferedImage.getHeight();
        return rectangle;
    }

    public void destroy(long delta){

    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }

    public void setBufferedImage(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;
    }

    public AffineTransform getWorldAffineTransform() {
        return worldAffineTransform;
    }

    public void setWorldAffineTransform(AffineTransform worldAffineTransform) {
        this.worldAffineTransform = worldAffineTransform;
    }
}
