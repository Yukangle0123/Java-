package model;

import view.MyPanel;

import java.awt.*;

public class Bullet {
    public MyPanel myPanel;

    public int width=8;
    public int height=8;

    public int x;
    public int y;

    public Image[]images=new Image[]{
            Toolkit.getDefaultToolkit().getImage("images/bullet01.png"),
            Toolkit.getDefaultToolkit().getImage("images/bullet02.png"),
            Toolkit.getDefaultToolkit().getImage("images/bullet03.png"),
            Toolkit.getDefaultToolkit().getImage("images/bullet04.png"),
            Toolkit.getDefaultToolkit().getImage("images/bullet05.png"),
            Toolkit.getDefaultToolkit().getImage("images/bullet06.png"),
    };
    public int index=0;
    public Bullet(MyPanel myPanel){
        this.myPanel=myPanel;
    }
    public void drawSelf(Graphics g){
        g.drawImage(this.images[index],x,y,width,height,null);

        if(this.myPanel.timer%1==0){
            index++;
            if(index==this.images.length){
                index=0;
            }
            y--;
            //当子弹跑出面板
            if(y<0) {
                myPanel.bullets.remove(this);
            }
        }
        //判断子弹是否打中敌人
        for(int i=0;i<this.myPanel.enemies.size();i++){
            Enemy e=this.myPanel.enemies.get(i);

            //判断
            if(this.x>=e.x-this.width&&this.x<=e.x+e.width
                    &&this.y>=e.y-this.height&&this.y<=e.y+e.height){
                //子弹销毁
                this.myPanel.bullets.remove(this);
                //敌机血量减少
                e.underAttack();
            }
        }
    }
}
