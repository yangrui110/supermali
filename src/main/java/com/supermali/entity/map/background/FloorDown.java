package com.supermali.entity.map.background;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * @project super-mali
 * @Date 2021/2/28
 * @Auth yangrui
 **/
public class FloorDown extends BackGroundMapAbstract {
    private static BufferedImage imageSky;
    private static byte[] imageBys;

    public FloorDown() {
    }

    public FloorDown(int x, int y) {
        super(x, y);
    }

    @Override
    public void init() {
        // 加载图片
        if(imageSky!=null){
            setBufferedImage(imageSky);
            setImage(imageBys);
        }else {
            byte[] imageBytes = this.getImageBytes("/img/map/background/floorDown.png");
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
