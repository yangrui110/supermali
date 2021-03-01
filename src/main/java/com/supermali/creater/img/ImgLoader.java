package com.supermali.creater.img;

import com.supermali.util.FileUtil;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class ImgLoader {

    private Map<Enum,ImgHelper> imgs;

    public ImgLoader() {
        this.imgs = new HashMap<>();
    }

    /**
     * 加载地图元素
     * */
    public void loadMapFactor(){}
    /**
     * 加载人物相关
     * */
    private void loadPerson(){
        // 加载陆地人物动作
        InputStream inputStream = this.getClass().getResourceAsStream("");
    }
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
}
