package com.supermali;

import com.supermali.creater.GameStarter;
import com.supermali.listener.KeyEventSupport;
import com.supermali.listener.MouseSupport;
import com.supermali.util.FrameRate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;

/**
 * @project super-mali
 * @Date 2021/2/24
 * @Auth yangrui
 **/
public class FrameWork extends JFrame implements Runnable {

    Canvas canvas ;
    BufferStrategy bufferStrategy ;
    Thread thread;
    KeyListener keyListener;
    MouseListener mouseListener;
    FrameRate frameRate;
    GameStarter gameStarter;

    private volatile boolean runningg = true;

    public void init(){
        frameRate = new FrameRate();
        frameRate.init();
        gameStarter = new GameStarter();
        gameStarter.setFrame(this);

        keyListener = new KeyEventSupport();
        mouseListener = new MouseSupport();
        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(1000,800));
        canvas.setIgnoreRepaint(true);
        canvas.addMouseListener(mouseListener);
        getContentPane().add(canvas);
        setIgnoreRepaint(true);
        setVisible(true);
        requestFocus();
        addKeyListener(keyListener);

        setResizable(false);
        pack();
        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        while(runningg){
            gameLoop();
        }
    }

    public void closeApp(){
        this.runningg = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }

    public void gameLoop(){
        do{
            do{
                Graphics graphics=null;
                try{
                    graphics = bufferStrategy.getDrawGraphics();
                    graphics.clearRect(0,0,getWidth(),getHeight());
                    render(graphics);
                }finally {
                    graphics.dispose();
                }
            }while (bufferStrategy.contentsRestored());
            bufferStrategy.show();
        } while (bufferStrategy.contentsLost());
    }
    // 绘制每一帧的图
    public void render(Graphics graphics){
        frameRate.calculate();
        gameStarter.proccessKey(frameRate.getDelta()); // 处理按键输入
        gameStarter.proccessMouse();
        gameStarter.show(graphics,frameRate.getDelta());
        frameRate.render(graphics);
    }
}
