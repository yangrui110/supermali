package com.supermali;

import com.supermali.creater.GameStarter;
import com.supermali.util.FrameRate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
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
    FrameRate frameRate;
    GameStarter gameStarter;

    private volatile boolean runningg = true;

    public void init(){
        frameRate = new FrameRate();
        frameRate.init();
        gameStarter = new GameStarter();

        keyListener = new KeyEventSupport();
        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(1000,800));
        canvas.setIgnoreRepaint(true);
        getContentPane().add(canvas);
        setIgnoreRepaint(true);
        setVisible(true);
        addKeyListener(keyListener);
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
        gameStarter.show(graphics);
        frameRate.render(graphics);
    }
}
