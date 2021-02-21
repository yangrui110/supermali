package com.supermali;

import javax.swing.*;
import java.awt.*;

/**
 * @project super-mali
 * @Date 2021/2/21
 * @Auth yangrui
 **/
public class MainRunner {

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        JPanel jPanel = new JPanel();
        jPanel.setBackground(Color.BLUE);
        frame.setContentPane(jPanel);
        frame.setVisible(true);
        frame.setSize(500,1000);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

}
