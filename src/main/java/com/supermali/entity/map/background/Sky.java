package com.supermali.entity.map.background;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * @project super-mali
 * @Date 2021/2/27
 * @Auth yangrui
 **/
public class Sky extends BackGroundMapAbstract {

    private static BufferedImage imageSky;
    private static byte[] imageBys;

    public Sky() {
    }

    public Sky(int x, int y) {
        super(x,y);
    }

    // 初始化，设置图片属性
    @Override
    public void init() {
        // 加载图片
        if(imageSky!=null){
            setBufferedImage(imageSky);
            setImage(imageBys);
        }else {
            byte[] imageBytes = this.getImageBytes("/img/map/background/sky.png");
            setImage(imageBytes);
            try {
                BufferedImage read = ImageIO.read(new ByteArrayInputStream(imageBytes));
                setBufferedImage(read);
                imageSky = read;
                imageBys = imageBytes;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
