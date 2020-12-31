package model;

import view.BaseFrame;
import view.MyPanel;

import java.awt.Toolkit;



public class Enemy008 extends Enemy{

	public Enemy008(MyPanel mypanel) {
		super(mypanel);
		width = 60;
		height = 40;
		x = (int)(Math.random() * (BaseFrame.frameWidth - width) );
		y = 0 - height;
		image = Toolkit.getDefaultToolkit().getImage("images/enemy08.png");
		speed = 2;
		hp = 8;
		this.items = new Item[]{
				new Item004(mypanel),
				new Item002(mypanel),
				new Item002(mypanel)
		};
	}

}
