package model;

import view.BaseFrame;
import view.MyPanel;

import java.awt.Toolkit;



public class Enemy006 extends Enemy {
	public Enemy006(MyPanel mypanel) {
		super(mypanel);
		width = 100;
		height = 65;
		x = (int)(Math.random() * (BaseFrame.frameWidth - width) );
		y = 0 - height;
		image = Toolkit.getDefaultToolkit().getImage("images/enemy06.png");
		speed = 3;
		hp = 8;
		
		this.items = new Item[]{
				new Item003(mypanel),
				new Item003(mypanel),
				new Item002(mypanel),
				new Item003(mypanel),
				new Item001(mypanel)
		};
	}
}
