package model;

import view.BaseFrame;
import view.MyPanel;

import java.awt.Toolkit;



public class Enemy002 extends Enemy {
	public Enemy002(MyPanel mypanel) {
		super(mypanel);
		width = 50;
		height = 40;
		x = (int)(Math.random() * (BaseFrame.frameWidth - width) );
		y = 0 - height;
		image = Toolkit.getDefaultToolkit().getImage("images/enemy02.png");
		speed = 3;
		hp = 3;
		
		this.items = new Item[]{
				new Item001(mypanel),
				new Item001(mypanel),
				new Item001(mypanel)
		};
	}
}
