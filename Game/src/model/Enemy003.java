package model;

import view.BaseFrame;
import view.MyPanel;

import java.awt.Toolkit;



public class Enemy003 extends Enemy {
	public Enemy003(MyPanel mypanel) {
		super(mypanel);
		width = 50;
		height = 37;
		x = (int)(Math.random() * (BaseFrame.frameWidth - width) );
		y = 0 - height;
		image = Toolkit.getDefaultToolkit().getImage("images/enemy03.png");
		speed = 3;
		hp = 3;	
		this.items = new Item[]{
				new Item001(mypanel),
				new Item002(mypanel),
				new Item002(mypanel)
		};
	}
}
