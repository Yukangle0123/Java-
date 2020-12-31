package model;

import JDBC.UserDao;
import thread.BulletMusicThread;
import thread.DrawableThread;
import util.GameException;
import view.BaseFrame;
import view.MyPanel;

import java.awt.*;

public class Enemy {
    public MyPanel myPanel;

    public int width;
    public int height;

    public int x;
    public int y;

    public Image image;

    public Image[]images=new Image[]{
            Toolkit.getDefaultToolkit().getImage("images/die01.png"),
            Toolkit.getDefaultToolkit().getImage("images/die01.png"),
            Toolkit.getDefaultToolkit().getImage("images/die01.png"),
            Toolkit.getDefaultToolkit().getImage("images/die01.png")
};
    public int index=0;

    public int hp;

    public int speed;//敌机的速度
    //存放当前敌机携带的道具
    public Item []items;
    public Enemy(MyPanel myPanel){
        this.myPanel=myPanel;
    }
    public void drawSelf(Graphics g){
        //判断敌机是否被击中
        if(hp>0){
            g.drawImage(this.image,x,y,width,height,null);
        }else{
            g.drawImage(images[index],x,y,45,45,null);
            if(myPanel.timer%10==0){
                index++;
                if(index>=this.images.length){
                    //敌机死亡
                    killed();
                    new BulletMusicThread("video/boom.wav").start();
                }
            }
        }
        //敌机根据速度向下移动
        if(this.myPanel.timer%speed==0){
            y++;
            if(y>= BaseFrame.frameHeight){
                this.myPanel.enemies.remove(this);
            }
        }
        // 判断敌机是否碰到玩家飞机
        Attack();
    }
    //被打死
    public void killed(){
        this.myPanel.enemies.remove(this);
        //如果携带道具 释放道具
        if(items!=null&&items.length>0){
            for(int i=0;i<items.length;i++){
                Item item=items[i];

                item.y=this.y;
                item.x=this.x+20*i;

                //将释放出的item添加到panel
                this.myPanel.items.add(item);
            }
        }

    }
    //处于被打中的状态
    public void underAttack(){
        if(hp>0){
            hp--;
        }
    }
    public void Attack(){
        //            if( x >e.x-width+30 &&x<e.x+e.width-30&&y>e.y-height+30 &&y<e.y+e.height-30){
        if( myPanel.player.x >this.x-myPanel.player.width+30 &&myPanel.player.x<this.x+this.width-30
                &&myPanel.player.y>this.y-myPanel.player.height+30 &&myPanel.player.y<this.y+this.height-30){
                if(this.myPanel.player.life<=1){
                    DrawableThread.isStop=true;
                    try {
                        UserDao userDao=new UserDao();
                        userDao.addScore(this.myPanel.player.count);
                    } catch (GameException e) {
                        e.printStackTrace();
                    }
                    return;
                }

                killed();
                this.myPanel.player.life--;
                new BulletMusicThread("video/boom.wav").start();

//                结束线程
//                DrawableThread.isStop=true;
//                return;

            }
        }
}
