package com.supermali.listener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @project super-mali
 * @Date 2021/2/25
 * @Auth yangrui
 **/
public class KeyEventSupport implements KeyListener{

    static int[] pressed = new int[256];

    @Override
    public synchronized void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        pressed[e.getKeyCode()]++;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        pressed[e.getKeyCode()]=0;
    }

    public static int getPressed(int keyCode) {
        return pressed[keyCode];
    }
}
