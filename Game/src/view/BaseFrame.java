package view;

import listener.FrameMouseMotionListener;
import model.*;

import javax.swing.*;
import java.awt.*;

public class BaseFrame extends JFrame {
    public static int frameWidth=512;
    public static int frameHeight=768;
    public MyPanel myPanel;

    public static boolean hasPlayer;
    public static boolean hasItem;
    public static boolean hasCount;
    //鼠标监听器
//    public FrameMouseListener frameMouseListener;
    public FrameMouseMotionListener frameMouseMotionListener;

//    public void setTouchListener(){
//        frameMouseListener=new FrameMouseListener();
//        this.frameMouseListener.baseFrame=this;
//
//        this.addMouseListener(frameMouseListener);
//    }
	public void setMouseMotionListener() {
		   this.frameMouseMotionListener=new FrameMouseMotionListener();
		   this.frameMouseMotionListener.baseFrame=this;
		   this.addMouseMotionListener(this.frameMouseMotionListener);
	}
    public BaseFrame(){
        super("Air War");
        //获得屏幕的分辨率
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        //设置窗口的大小和位置
        setBounds((int)(screenSize.getWidth()-frameWidth)/2,0,frameWidth,frameHeight);
        //设置布局方式
        setLayout(null);
        //设置监听器
//        setTouchListener();
        setMouseMotionListener();


        myPanel=new MyPanel(this);
        this.myPanel.setBounds(0,0,frameWidth,frameHeight);
        this.add(this.myPanel);
        //添加敌机的类型
        addEnemyType(Enemy001.class);
        addEnemyType(Enemy002.class);
        addEnemyType(Enemy003.class);
        addEnemyType(Enemy004.class);
        addEnemyType(Enemy005.class);
        addEnemyType(Enemy006.class);
        addEnemyType(Enemy007.class);
        addEnemyType(Enemy008.class);


        //设置窗口关闭行为
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //显示窗口
        setVisible(true);
    }
    public  void setPlayer(){
        hasPlayer=true;
    }
    public  void setItem(){
        hasItem=true;
    }
    public void setCount(){
        hasCount=true;
    }
    public void setPlayerPowerLevel(int attackMode){
        this.myPanel.player.attackMode=attackMode;
    }
    public void addEnemyType(Class c){
        this.myPanel.enemyType.add(c);
    }

}
