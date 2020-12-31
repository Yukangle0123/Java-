package model;

import view.MyPanel;

import java.awt.*;

public class Item001 extends Item  {
    public Item001(MyPanel myPanel){
        super(myPanel);
    this.width=15;
    this.height=15;
    this.images=new Image[]{
            Toolkit.getDefaultToolkit().getImage("images/star01.png"),
            Toolkit.getDefaultToolkit().getImage("images/star02.png"),
            Toolkit.getDefaultToolkit().getImage("images/star03.png"),
            Toolkit.getDefaultToolkit().getImage("images/star04.png"),
            Toolkit.getDefaultToolkit().getImage("images/star05.png"),
            Toolkit.getDefaultToolkit().getImage("images/star06.png"),
            Toolkit.getDefaultToolkit().getImage("images/star07.png"),
            Toolkit.getDefaultToolkit().getImage("images/star08.png"),
            Toolkit.getDefaultToolkit().getImage("images/star09.png")
        };

        this.imageSpeed=(int)(Math.random()*20+10);

        this.speed=3;
        this.count=20;
    }
}
