package com.supermali.entity.npc.person;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * @project super-mali
 * @Date 2021/2/28
 * @Auth yangrui
 **/
public class Person extends PersonAbstract {

    private long rate = 0;

    double t = 0.05; // 间隔时间

    double totalTime = 0; // 总时间

    boolean jumpOver = true;

    public Person() {
        super();
    }

    public Person(int x, int y) {
        super(x, y);
    }

    @Override
    public void make(Graphics g) {
        // 选择需要画出的图片
        BufferedImage bufferedImage = null;
        if(this.getEnviroment()==Enviroment.LAND){
            // 检测跳跃是否结束
            if(!jumpOver){
                // 竖直上抛运动
                totalTime+=t;
                double dy = 60*t - 10*t*totalTime;
                double v2 = this.getY() + dy;
                this.setY((int) v2);
                if(this.getY()<32){
                    this.totalTime = 0;
                    jumpOver = true;
                }
                bufferedImage = getJumpImg();
                // 上抛结束
            }else {
                // 如果跳跃结束，就判断当前的状态
                if (this.getRunningState() == RunningState.FORWARD) {
                    // 获取到显示的图片
                    BufferedImage[] runImgs = getRunImgs();
                    int index = getRunImgIndex();
                    BufferedImage runImg = runImgs[index % runImgs.length];
                    bufferedImage = runImg;

                } else if (this.getRunningState() == RunningState.TERMINATE) {
                    bufferedImage = getTerminateImg();
                } else if (this.getRunningState() == RunningState.JUMP) {
                    bufferedImage = getJumpImg();
                    jumpOver = false;
                }
            }
        }
        // 画图
        if(bufferedImage!=null) {
            this.setBufferedImage(bufferedImage);
            super.make(g);
            g.drawString("用户坐标：x="+this.getX()+" y="+this.getY()+" 总时间：t="+totalTime,70,30);
        }
    }


    @Override
    public void checkCollide() {

    }

    @Override
    public void changeState() {

    }

    @Override
    public void jump() {
        this.setRunningState(RunningState.JUMP);
    }

    @Override
    public void moveForward(long delta) {
        if(RunningState.JUMP==getRunningState()) return; // 跳跃状态，直接返回
        long l = delta / 60;
        if(l==0||l>rate) {
            rate = l;
            int state = getRunImgIndex();
            state++;
            setRunImgIndex(state);
            this.setRunningState(RunningState.FORWARD);
        }
    }

    @Override
    public void down() {

    }

    @Override
    public void goback() {

    }

    @Override
    public void terminateMove() {
        this.setRunningState(RunningState.TERMINATE);
    }

    @Override
    public void createShape() {
//        super.createShape();
    }

    @Override
    public void init() {
        initAttr();
        BufferedImage[] runImgs = this.getRunImgs();
        byte[][] runImgBytes = this.getRunImgBytes();
        byte[] bytes0 = this.getImageBytes("/img/map/npc/person/mali/runImgIndex-0.png");
        byte[] bytes1 = this.getImageBytes("/img/map/npc/person/mali/runImgIndex-1.png");
        byte[] bytes2 = this.getImageBytes("/img/map/npc/person/mali/runImgIndex-2.png");


        byte[] bytes3 = this.getImageBytes("/img/map/npc/person/mali/terminate.png");
        byte[] bytes4 = this.getImageBytes("/img/map/npc/person/mali/jump.png");

        try {
            runImgs[0] = ImageIO.read(new ByteArrayInputStream(bytes0));
            runImgs[1] = ImageIO.read(new ByteArrayInputStream(bytes1));
            runImgs[2] = ImageIO.read(new ByteArrayInputStream(bytes2));
            BufferedImage terminate = ImageIO.read(new ByteArrayInputStream(bytes3));
            BufferedImage jump = ImageIO.read(new ByteArrayInputStream(bytes4));
            this.setTerminateImg(terminate);
            this.setJumpImg(jump);
            runImgBytes[0] = bytes0;
            runImgBytes[1] = bytes1;
            runImgBytes[2] = bytes2;
            this.setTerminateBytes(bytes3);
            this.setJumpImgBytes(bytes4);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
