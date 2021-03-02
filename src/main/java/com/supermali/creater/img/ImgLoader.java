package com.supermali.creater.img;

import com.supermali.util.FileUtil;
import com.supermali.util.ImgConvertUtil;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ImgLoader {

    private static Map<Enum,ImgHelper> imgs;

    public static Map<Enum, ImgHelper> getImgs() {
        return imgs;
    }

    public ImgLoader() {
        this.imgs = new HashMap<>();
    }

    public static ImgHelper getImgHelper(Enum key){
        return imgs.get(key);
    }

    /**
     * 加载地图元素
     * */
    public void loadMapFactor(){
        loadPerson();
        loadBackground();
    }
    /**
     * 加载人物相关图片
     * */
    private void loadPerson(){
        // 加载陆地人物
        // 前进
        List<String> runImgIndex = Arrays.asList(
                "/img/map/npc/person/mali/runImgIndex-0.png",
                "/img/map/npc/person/mali/runImgIndex-1.png",
                "/img/map/npc/person/mali/runImgIndex-2.png");
        ImgHelper forward = createImgHelper(runImgIndex);
        imgs.put(ImgKey.Land.FORWARD,forward);
        // 静止向右
        ImgHelper terminateRight = createImgHelper("/img/map/npc/person/mali/terminate.png");
        imgs.put(ImgKey.Land.TERMINATE, terminateRight);
        // 右跳跃
        ImgHelper jumpRight = createImgHelper("/img/map/npc/person/mali/jump.png");
        imgs.put(ImgKey.Land.JUMP, jumpRight);
        // 后退
        List<String> goubackIndex = Arrays.asList(
                "/img/map/npc/person/mali/runImgIndex-0.png",
                "/img/map/npc/person/mali/runImgIndex-1.png",
                "/img/map/npc/person/mali/runImgIndex-2.png");
        ImgHelper gouback = createImgHelper(goubackIndex, true);
        imgs.put(ImgKey.Land.GOBACK,gouback);
    }
    /**
     * 加载monistor相关的图片
     * */
    private void loadMonistor(){

    }
    /**
     * 加载背景图片
     * */
    private void loadBackground(){
        // 地板之下的蓝色部分
        ImgHelper floorDown = createImgHelper("/img/map/background/floorDown.png");
        imgs.put(ImgKey.Land.FLOOR_DOWN, floorDown);
        // 加载天空
        ImgHelper sky = createImgHelper("/img/map/background/sky.png");
        imgs.put(ImgKey.Land.SKY, sky);
        // 加载地板
        ImgHelper floor = createImgHelper("/img/map/hinder/floor.png");
        imgs.put(ImgKey.Land.FLOOR, floor);
        // 加载可顶的问号
        ImgHelper question = createImgHelper("/img/map/hinder/Question.png");
        imgs.put(ImgKey.Land.QUESTION, question);
    }

    private ImgHelper createImgHelper(String path){
        ImgHelper imgHelper = createImgHelper(path, false);
        return imgHelper;
    }
    private ImgHelper createImgHelper(String path, boolean isMirror){
        try {
            ImgHelper imgHelper = new ImgHelper(1);
            InputStream inputStream = this.getClass().getResourceAsStream(path);
            byte[] bytes = FileUtil.readFileToByte(inputStream);
            byte[][] bytes1 = new byte[1][];
            bytes1[0] = bytes;
            imgHelper.setBytes(bytes1);
            ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(bytes);
            BufferedImage read = ImageIO.read(arrayInputStream);
            arrayInputStream.close();
            BufferedImage[] images = new BufferedImage[1];
            if(isMirror) {
                images[0] = ImgConvertUtil.mirror(read);
            }else {
                images[0]=read;
            }
            imgHelper.setBufferedImage(images);
            return imgHelper;
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private ImgHelper createImgHelper(List<String> paths){
        ImgHelper imgHelper = createImgHelper(paths, false);
        return imgHelper;
    }

    /**
     * @param isMirror 图像是否镜像
     * */
    private ImgHelper createImgHelper(List<String> paths,boolean isMirror){
        try {
            int len = paths.size();
            ImgHelper imgHelper = new ImgHelper(len);
            byte[][] bytes1 = new byte[len][];
            BufferedImage[] images = new BufferedImage[len];
            for(int i=0;i<len;i++){
                String path = paths.get(i);
                InputStream inputStream = this.getClass().getResourceAsStream(path);
                byte[] bytes = FileUtil.readFileToByte(inputStream);
                bytes1[i] = bytes;
                ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(bytes);
                BufferedImage read = ImageIO.read(arrayInputStream);
                arrayInputStream.close();
                if(isMirror) {
                    images[i] = ImgConvertUtil.mirror(read);
                }else {
                    images[i]=read;
                }
            }
            imgHelper.setBytes(bytes1);
            imgHelper.setBufferedImage(images);
            return imgHelper;
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
