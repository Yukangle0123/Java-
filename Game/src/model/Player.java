package model;

import thread.DrawableThread;
import view.BaseFrame;
import view.MyPanel;

import java.awt.*;

public class Player {
    public MyPanel myPanel;

    public int width=100;
    public int height=100;

    public int x;
    public int y;

    //设置玩家火力等级
    public int attackMode=0;

    public int life=3;

    public Image[] images=new Image[]{
            Toolkit.getDefaultToolkit().getImage("images/player01.png"),
            Toolkit.getDefaultToolkit().getImage("images/player02.png"),
            Toolkit.getDefaultToolkit().getImage("images/player03.png"),
            Toolkit.getDefaultToolkit().getImage("images/player04.png"),
            Toolkit.getDefaultToolkit().getImage("images/player05.png"),
            Toolkit.getDefaultToolkit().getImage("images/player06.png"),
            Toolkit.getDefaultToolkit().getImage("images/player07.png"),
            Toolkit.getDefaultToolkit().getImage("images/player08.png"),
            Toolkit.getDefaultToolkit().getImage("images/player09.png")
    };
    public int imageIndex=0;

    public int count;
    public Player(MyPanel myPanel){
        this.myPanel=myPanel;
        this.x= (BaseFrame.frameWidth-this.width)/2;
        this.y=BaseFrame.frameHeight-this.height*2;

    }
    public void drawSelf(Graphics g){
        g.drawImage(this.images[imageIndex],x,y,width,height,null);
        if(this.myPanel.timer%50==0){
            imageIndex++;
            if(this.imageIndex==images.length) {
                imageIndex = 0;
            }
        }
        //判断玩家是否吃到道具
        for(int i=0;i<this.myPanel.items.size();i++){
            Item item=this.myPanel.items.get(i);


            if((this.x>=item.x-this.width && this.x<=item.x+item.width) &&
                    (this.y>=item.y-this.height && this.y<=item.y+item.height)) {
                item.eated();
            }
        }
        // 判断飞机是否碰到敌机
//        for( int i = 0 ; i <= this.myPanel.enemies.size() - 1; i++ ) {
//            Enemy e = this.myPanel.enemies.get(i);
//            if( x >e.x-width+30 &&x<e.x+e.width-30&&y>e.y-height+30 &&y<e.y+e.height-30){
////                if(this.life==0){
////                    DrawableThread.isStop=true;
////                    return;
////                }
////                life--;
//
//                //结束线程
//                DrawableThread.isStop=true;
//                return;
//
//            }
//        }

    }
}
