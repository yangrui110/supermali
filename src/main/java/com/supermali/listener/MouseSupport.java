package com.supermali.listener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @project super-mali
 * @Date 2021/3/1
 * @Auth yangrui
 **/
public class MouseSupport extends MouseAdapter {

    private static boolean mouseClick = false; // 鼠标点击只需要控制窗口聚焦

    @Override
    public void mouseClicked(MouseEvent e) {
        mouseClick = true;
    }

    public static boolean isMouseClick() {
        return mouseClick;
    }

    public static void setMouseClick(boolean mouseClick) {
        MouseSupport.mouseClick = mouseClick;
    }
}
