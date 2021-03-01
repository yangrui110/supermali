package com.supermali.entity.npc.person;

import com.supermali.entity.MapImageAbstract;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

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
                this.setY(v2);
                boolean checkCollide = this.checkCollide(getHinders());
                if(checkCollide){
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
                }else if (this.getRunningState() == RunningState.DOWN) {
                    double y = this.getY();
                    y--;
                    this.setY(y);
                    bufferedImage = getTerminateImg();
                }
            }
        }
        // 画图
        if(bufferedImage!=null) {
            this.setBufferedImage(bufferedImage);
            boolean checkCollide = this.checkCollide(getHinders());
            if(!checkCollide){
                this.setRunningState(RunningState.DOWN); // 设置状态时下落
            }
            super.make(g);
            g.drawString("用户坐标：x="+this.getX()+" y="+this.getY()+" 总时间：t="+totalTime,70,30);
        }
    }


    @Override
    public boolean checkCollide() {
        Shape shape = this.getShape();
        Rectangle rectangle = (Rectangle) shape;
        for(MapImageAbstract npcAbstract: getHinders()){
            double npcAbstractX = npcAbstract.getX();
            double npcAbstractY = npcAbstract.getY();
            if(npcAbstract.getShape().intersects(rectangle)){
                if(this.getY()>npcAbstractY){
                    // 人物在其上，将人物的竖直坐标转为16的倍数
                    double y = this.getY();
                    for(int i=(int)y;i<Integer.MAX_VALUE;i++){
                        if(i%16==0){
                            y = i;
                            break;
                        }
                    }
                    this.setY(y);
                }else {
                    double y = this.getY();
                    for(int i=(int)y;i>Integer.MIN_VALUE;i--){
                        if(i%16==0){
                            y = i;
                            break;
                        }
                    }
                    System.out.println(y);
                    this.setY(y);
                }
                return true;
            }
        }
        return false;
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

        // 前进
        double x = this.getX();
        if(x <500){
            x+=0.1;
            this.setX(x);
        }
        // 控制切换图片的帧率
        long l = delta / 60;
        if(l==0||l>rate) {
            // 帧率
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
