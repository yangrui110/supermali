package com.supermali;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @project super-mali
 * @Date 2021/2/21
 * @Auth yangrui
 **/
public class MainRunner {

    public static void main(String[] args) {
        FrameWork frameWork = new FrameWork();
        frameWork.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frameWork.closeApp();
            }
        });
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                frameWork.init();
            }
        });
    }

}
