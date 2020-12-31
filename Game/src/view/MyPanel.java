package view;

import model.Bullet;
import model.Enemy;
import model.Item;
import model.Player;
import thread.BulletMusicThread;
import thread.DrawableThread;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class MyPanel extends JPanel {
    public Image bgImage;
    public int timer=0;
    public int top=0;
    public DrawableThread drawableThread;

    public Player player;

    public ArrayList<Bullet> bullets = new ArrayList<>();

    public ArrayList<Enemy> enemies=new ArrayList<>();

    //存放所有的敌机类型
    public ArrayList<Class> enemyType=new ArrayList<>();

    public ArrayList<Item> items=new ArrayList<>();

    public BaseFrame baseFrame;

    public static final int START = 0;     //启动状态
    public static final int RUNNING = 1;   //运行状态
    public static final int PAUSE = 2;     //暂停状态
    public static final int GAME_OVER = 3; //游戏结束状态
    public static int state = START; //当前状态(默认启动状态)

    public MyPanel(BaseFrame baseFrame){
        this.baseFrame=baseFrame;
        bgImage=Toolkit.getDefaultToolkit().getImage("images/bg01.jpg");

        player=new Player(this);
        this.drawableThread=new DrawableThread(this);
        DrawableThread.isStop=false;
        drawableThread.start();
    }

    //生成玩家
    public void entryPlayer(Graphics g){
        this.player.drawSelf(g);
    }
    //生成子弹
    public void createBullet(Graphics g){
        if(timer%100==0){
            if(this.player.count>=20000){
                Bullet bullet=new Bullet(this);
                bullet.x=this.player.x+this.player.width/2-bullet.width/2;
                bullet.y=this.player.y;
                this.bullets.add(bullet);
                new BulletMusicThread("video/bullet.wav").start();
            }else if(this.player.count>=5000){
                Bullet bullet1=new Bullet(this);
                bullet1.x=this.player.x+this.player.width/2-bullet1.width/2;
                bullet1.y=this.player.y-15;
                this.bullets.add(bullet1);

                Bullet bullet2=new Bullet(this);
                bullet2.x=this.player.x+this.player.width/2-bullet2.width/2- bullet2.width - 5;
                bullet2.y=this.player.y;
                this.bullets.add(bullet2);


                Bullet bullet3=new Bullet(this);
                bullet3.x=this.player.x+this.player.width/2- bullet3.width/2 + bullet3.width + 5;
                bullet3.y=this.player.y;
                this.bullets.add(bullet3);
                new BulletMusicThread("video/bullet.wav").start();
            }else{
                Bullet bullet1 = new Bullet(this);
                bullet1.x = player.x + player.width / 2 - bullet1.width/2;
                bullet1.y = player.y - 30;
                bullets.add(bullet1);

                Bullet bullet2 = new Bullet(this);
                bullet2.x = player.x + player.width / 2 - bullet2.width/2 - bullet2.width - 5;
                bullet2.y = player.y - 15;
                bullets.add(bullet2);

                Bullet bullet3 = new Bullet(this);
                bullet3.x = player.x + player.width / 2 - bullet3.width/2 + bullet3.width + 5;
                bullet3.y = player.y - 15;
                bullets.add(bullet3);

                Bullet bullet4 = new Bullet(this);
                bullet4.x = player.x + player.width / 2 - bullet4.width/2 - 2*bullet4.width - 10;
                bullet4.y = player.y;
                bullets.add(bullet4);

                Bullet bullet5 = new Bullet(this);
                bullet5.x = player.x + player.width / 2 - bullet5.width/2 + 2*bullet5.width +10;
                bullet5.y = player.y;
                bullets.add(bullet5);
                new BulletMusicThread("video/bullet.wav").start();

            }
        }
        for(int i=0;i<bullets.size();i++){
            this.bullets.get(i).drawSelf(g);
        }
    }
    //创建敌机
    public void createEnemy(Graphics g){
        if(this.timer%200==0){
            if(this.enemyType.size()>0){
                //随机一种敌机类型对应的下标
                int index=(int)(Math.random()*this.enemyType.size());
                Enemy enemy=null;
                try {
                    enemy=(Enemy) this.enemyType.get(index).getConstructors()[0].newInstance(new Object[]{this});
                } catch (InstantiationException |IllegalAccessException|InvocationTargetException e) {
                    e.printStackTrace();
                }
                this.enemies.add(enemy);
            }
        }
        //画敌机
        for(int i=0;i<this.enemies.size();i++){
            this.enemies.get(i).drawSelf(g);
        }
    }
    public void pointItem(Graphics g){
        for (int i = 0; i < this.items.size(); i++) {
            this.items.get(i).drawSelf(g);
        }
    }
    public void pointScoreAndLife(Graphics g){
        g.setColor(Color.white);
        g.drawString("score:"+this.player.count,BaseFrame.frameWidth-100,15);
        g.drawString("life:"+this.player.life,BaseFrame.frameWidth-100,30);
    }
    public void createBackGround(Graphics g){
        //绘制背景图
        g.drawImage(bgImage,0,top-bgImage.getHeight(this),bgImage.getWidth(this),bgImage.getHeight(this),null);
        g.drawImage(bgImage,0,top,bgImage.getWidth(this),bgImage.getHeight(this),null);
        timer++;
        if(timer==10000){
            timer=0;
        }
        //实现图片向下移动
        if(timer%10==0){
            top++;
            if(top>this.bgImage.getHeight(this)){
                top=0;
            }
        }
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        //绘制背景图
        createBackGround(g);
            //创建玩家
            entryPlayer(g);
            //创建子弹
            createBullet(g);
            //创建敌机
            createEnemy(g);
            //画道具
            pointItem(g);
            pointScoreAndLife(g);

    }
}
