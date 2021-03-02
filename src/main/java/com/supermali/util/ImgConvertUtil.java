package com.supermali.util;

import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @project super-mali
 * @Date 2021/3/1
 * @Auth yangrui
 **/
public class ImgConvertUtil {
    /**
     * 镜像
     */
    public static BufferedImage mirror(BufferedImage bufImage){

        // 获取图片的宽高
        final int width = bufImage.getWidth();
        final int height = bufImage.getHeight();

        // 读取出图片的所有像素
        int[] rgbs = bufImage.getRGB(0, 0, width, height, null, 0, width);

        // 对图片的像素矩阵进行水平镜像
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width / 2; col++) {
                int temp = rgbs[row * width + col];
                rgbs[row * width + col] = rgbs[row * width + (width - 1 - col)];
                rgbs[row * width + (width - 1 - col)] = temp;
            }
        }

        // 把水平镜像后的像素矩阵设置回 bufImage
        int type = bufImage.getType();
        BufferedImage bufferedImage = new BufferedImage(width, height, type);
        bufferedImage.setRGB(0,0,width,height,rgbs,0,width);
        return bufferedImage;
    }
}
