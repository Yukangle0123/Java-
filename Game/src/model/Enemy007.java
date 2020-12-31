package model;

import view.BaseFrame;
import view.MyPanel;

import java.awt.Toolkit;


public class Enemy007 extends Enemy{

	public Enemy007(MyPanel mypanel) {
		super(mypanel);
		width = 90;
		height = 60;
		x = (int)(Math.random() * (BaseFrame.frameWidth - width) );
		y = 0 - height;
		image = Toolkit.getDefaultToolkit().getImage("images/enemy07.png");
		speed = 2;
		hp = 6;
		this.items = new Item[]{
				new Item004(mypanel),
				new Item001(mypanel),
				new Item002(mypanel),
				new Item003(mypanel),
				new Item004(mypanel)
		};
	}

}
