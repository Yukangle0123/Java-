package model;

import view.BaseFrame;
import view.MyPanel;

import java.awt.*;

public class Enemy001 extends Enemy {
    public Enemy001(MyPanel myPanel) {
        super(myPanel);

        this.width=60;
        this.height=40;

        this.x=(int)(Math.random()*(BaseFrame.frameWidth-width));
        this.y=-40;

        this.speed=3;

        this.hp=3;

        this.image= Toolkit.getDefaultToolkit().getImage("images/enemy01.png");
        this.items=new Item[]{
                new Item001(myPanel),
                new Item001(myPanel),
                new Item001(myPanel)
        };


    }

}
