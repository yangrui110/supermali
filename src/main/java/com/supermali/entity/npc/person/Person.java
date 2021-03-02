package com.supermali.entity.npc.person;

import com.supermali.behavior.down.DownBehavior;
import com.supermali.behavior.down.land.LandPersonDown;
import com.supermali.behavior.forward.MoveForwordBehavior;
import com.supermali.behavior.forward.land.LandPersonMoveForword;
import com.supermali.behavior.goback.GobackBehavior;
import com.supermali.behavior.goback.land.LandPersonGoBack;
import com.supermali.behavior.jump.JumpBehavior;
import com.supermali.behavior.jump.land.LandPersonJump;
import com.supermali.behavior.squatDown.SquatDownBehavior;
import com.supermali.behavior.squatDown.land.LandPersonSquat;
import com.supermali.behavior.terminate.TerminateBehavior;
import com.supermali.behavior.terminate.land.LandTerminate;
import com.supermali.creater.img.ImgHelper;
import com.supermali.creater.img.ImgKey;
import com.supermali.creater.img.ImgLoader;
import com.supermali.entity.MapImageAbstract;
import com.supermali.entity.npc.NPCAbstract;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * @project super-mali
 * @Date 2021/2/28
 * @Auth yangrui
 **/
public class Person extends NPCAbstract {

    private List<? extends MapImageAbstract> hinders;

    // 前进行为
    MoveForwordBehavior forwordBehavior;
    // 后退行为
    GobackBehavior gobackBehavior;
    // 下降行为
    DownBehavior downBehavior;
    // 跳跃行为
    JumpBehavior jumpBehavior;
    // 静止行为
    TerminateBehavior terminateBehavior;
    // 下蹲行为
    SquatDownBehavior squatDownBehavior;

    private Direct direct;

    public enum Direct{
        Left,Right
    }

    public Person() {
        super();
    }

    public Person(int x, int y) {
        super(x, y);
    }

    @Override
    public void make(Graphics g) {
        super.make(g);
        g.drawString("用户坐标：x="+this.getX()+" y="+this.getY(),70,30);
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
                    this.setY(y);
                }
                return true;
            }
        }
        return false;
    }

    public boolean isLeft() {
        return direct==Direct.Left;
    }

    public void setDirect(Direct direct) {
        this.direct = direct;
    }

    @Override
    public void init() {
        hinders = new ArrayList<>();
        forwordBehavior = new LandPersonMoveForword(this);
        gobackBehavior = new LandPersonGoBack(this);
        jumpBehavior = new LandPersonJump(this);
        downBehavior = new LandPersonDown(this);
        terminateBehavior = new LandTerminate(this);
        squatDownBehavior=new LandPersonSquat(this);
        this.direct = Direct.Right;

        ImgHelper imgHelper = ImgLoader.getImgHelper(ImgKey.Land.TERMINATE);
        BufferedImage select = imgHelper.select(0);
        this.setBufferedImage(select);

    }

    public List<? extends MapImageAbstract> getHinders() {
        return hinders;
    }

    public void setHinders(List<? extends MapImageAbstract> hinders) {
        this.hinders = hinders;
    }

    public MoveForwordBehavior getForwordBehavior() {
        return forwordBehavior;
    }

    public GobackBehavior getGobackBehavior() {
        return gobackBehavior;
    }

    public DownBehavior getDownBehavior() {
        return downBehavior;
    }

    public JumpBehavior getJumpBehavior() {
        return jumpBehavior;
    }

    public TerminateBehavior getTerminateBehavior() {
        return terminateBehavior;
    }

    public SquatDownBehavior getSquatDownBehavior() {
        return squatDownBehavior;
    }
}
