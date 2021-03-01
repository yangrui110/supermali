package com.supermali.entity.npc.person;

import com.supermali.entity.MapImageAbstract;
import com.supermali.entity.npc.NPCAbstract;
import lombok.Data;

import java.awt.image.BufferedImage;
import java.util.List;

/**
 * @project super-mali
 * @Date 2021/2/28
 * @Auth yangrui
 **/
@Data
public abstract class PersonAbstract extends NPCAbstract {

    public PersonAbstract() {
        super();
    }

    public PersonAbstract(int x, int y) {
        super(x, y);
    }

    public void initAttr(){
        runImgs = new BufferedImage[3];
        runImgBytes = new byte[3][];
        enviroment = Enviroment.LAND;
        runningState = RunningState.TERMINATE;
        name = "mali";
        bloodSize = 1000;
        runImgIndex = 0;
        state = PersonState.SMALL;
        lifes = 3;
    }
    private List<? extends MapImageAbstract> hinders;
    // 人物名称
    private String name;
    // 人物血量
    private int bloodSize;
    // 人物状态 大/小
    private PersonState state;
    // 人物跑步状态，控制运行时的图片展示
    private int runImgIndex;
    // 保存人物跑步时的图片()
    private BufferedImage[] runImgs;
    private byte[][] runImgBytes;
    // 人物停止时的图片
    private BufferedImage terminateImg;
    private byte[] terminateBytes;
    // 人物跳跃时的图片
    private BufferedImage jumpImg;
    private byte[] jumpImgBytes;
    // 人物所处环境
    private Enviroment enviroment;
    // 运动状态
    private RunningState runningState;
//    private BulletInterface bullet;
    // 人物分数
    private int score;
    // 人物的生命数
    private int lifes;

    enum PersonState{
        SMALL,
        GROWTH,
        FIRE
    }
    enum Enviroment{
        WATER("水下"),
        LAND("陆地");
        private String desc;
        Enviroment(String desc) {
            this.desc = desc;
        }
    }
    enum RunningState{
        FORWARD("前进"),
        GOBACK("后退"),
        TERMINATE("停止移动"),
        JUMP("跳跃"),
        DOWN("下蹲");
        private String desc;

        RunningState(String desc) {
            this.desc = desc;
        }
    }
}
