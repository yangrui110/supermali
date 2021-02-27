package com.supermali.entity;

import com.supermali.util.FileUtil;
import com.supermali.util.WorldTransform;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
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
    int x;
    // y坐标
    int y;
    // 图像数组
    byte[] image;

    private BufferedImage bufferedImage;
    // 图像对应的shape形状
    private Shape shape;

    private AffineTransform worldAffineTransform;

    public MapImageAbstract() {
        this.init();
        this.worldAffineTransform = WorldTransform.getWorldAffineTransform();
    }

    public MapImageAbstract(int x, int y) {
        this.x = x;
        this.y = y;
        this.init();
        this.worldAffineTransform = WorldTransform.getWorldAffineTransform();
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

    public abstract void init();

    public void loadImg(String path){
        try {
            InputStream inputStream = this.getClass().getResourceAsStream(path);
            byte[] bytes = FileUtil.readFileToByte(inputStream);
            this.image = bytes;
            bufferedImage = ImageIO.read(new ByteArrayInputStream(image));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public byte[] getImage() {
        return image;
    }

    public Shape getShape() {
        return shape;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
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
}
