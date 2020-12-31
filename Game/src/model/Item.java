package model;

import view.BaseFrame;
import view.MyPanel;

import java.awt.*;

public class Item {
    public MyPanel myPanel;

    public int width;
    public int height;

    public int x;
    public int y;

    public Image[]images;

    public int imageSpeed;

    public int speed;
    public int index=0;

    public int count;

    public Item(MyPanel myPanel){
        this.myPanel=myPanel;
    }
    public void drawSelf(Graphics g){
        g.drawImage(images[index],x,y,width,height,null);
        if(this.myPanel.timer%imageSpeed==0){
            index++;
            if(index==this.images.length){
                index=0;
            }
        }
        if(this.myPanel.timer%speed==0){
            y++;
            if(this.y>= BaseFrame.frameHeight){
                this.myPanel.items.remove(this);
            }
        }
    }
    //道具被吃掉
    public void eated(){
        this.myPanel.player.count+=this.count;
        this.myPanel.items.remove(this);
    }
}
