package com.supermali.entity;

import com.supermali.util.FileUtil;
import com.supermali.util.WorldTransform;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

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

    private BufferedImage bufferedImage;
    // 图像对应的shape形状
    private Shape shape;

    private AffineTransform worldAffineTransform;

    public MapImageAbstract() {
        this.config();

    }

    public MapImageAbstract(int x, int y) {
        this.x = x;
        this.y = y;
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
     * 子类复写的方法
     * */
    public abstract void init();

    /**
     * 加载路径
     * */
    public byte[] getImageBytes(String path){
        try {
            InputStream inputStream = this.getClass().getResourceAsStream(path);
            byte[] bytes = FileUtil.readFileToByte(inputStream);
            return bytes;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 生成shape，默认生成矩形
     * */
    public void createShape(){
        Rectangle rectangle = new Rectangle();
        rectangle.x =(int)this.x;
        rectangle.y=(int)this.y;
        rectangle.width = bufferedImage.getWidth();
        rectangle.height=bufferedImage.getHeight();
        this.shape = rectangle;
    }

    /**
     * 配置
     * */
    public void config(){
        this.init();
        this.createShape();
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

    public Shape getShape() {
        Rectangle rectangle = new Rectangle();
        rectangle.setLocation((int)x,(int)y);
        rectangle.width = bufferedImage.getWidth();
        rectangle.height = bufferedImage.getHeight();
        return rectangle;
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
