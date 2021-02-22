package com.supermali;

import javax.swing.*;

/**
 * @project super-mali
 * @Date 2021/2/22
 * @Auth yangrui
 **/
public class FrameWork {

    public void show(){
        JFrame jFrame = new JFrame();
        jFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        jFrame.setVisible(true);
        jFrame.setSize(500,1000);

//        JPanel jPanel = new JPanel();
        CanvasCircle canvasCircle = new CanvasCircle();
//        canvasCircle.rotate();
//        jFrame.add(jPanel);
        jFrame.add(canvasCircle);

    }

}
