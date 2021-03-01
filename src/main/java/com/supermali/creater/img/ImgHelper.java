package com.supermali.creater.img;

import lombok.Data;

import java.awt.image.BufferedImage;

/**
 * 图片帮助类
 * */
@Data
public class ImgHelper {

    BufferedImage[] bufferedImage;

    byte[][] bytes;

    public ImgHelper(int len) {
        this.bufferedImage = new BufferedImage[len];
        this.bytes = new byte[len][];
    }

    public BufferedImage select(int i){
        int i1 = i % bufferedImage.length;
        return bufferedImage[i1];
    }

}
