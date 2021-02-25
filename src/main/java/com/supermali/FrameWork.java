package com.supermali;

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

    private TestCircle testCircle;

    private volatile boolean runningg = true;

    public void init(){
        keyListener = new KeyEventSupport();
        this.testCircle = new TestCircle();
        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(500,800));
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
        long before = System.currentTimeMillis();
        do{
            do{
                Graphics graphics=null;
                try{
                    graphics = bufferStrategy.getDrawGraphics();
                    graphics.clearRect(0,0,getWidth(),getHeight());
                    render(graphics);
                    testCircle.drawCircle(graphics);
                    long now = System.currentTimeMillis();
//                    graphics.drawString(""+(now-before),100,100);
//                    graphics.drawString("呵呵呵呵呵呵",200,100);
                }finally {
                    graphics.dispose();
                }
            }while (bufferStrategy.contentsRestored());
            bufferStrategy.show();
        } while (bufferStrategy.contentsLost());
    }
    // 绘制每一帧的图
    public void render(Graphics graphics){

    }
}
