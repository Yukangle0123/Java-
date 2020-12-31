package model;

import view.BaseFrame;
import view.MyPanel;

import java.awt.Toolkit;



public class Enemy009 extends Enemy{

	public Enemy009(MyPanel mypanel) {
		super(mypanel);
		width = 124;
		height = 100;
		x = (int)(Math.random() * (BaseFrame.frameWidth - width) );
		y = 0 - height;
		image = Toolkit.getDefaultToolkit().getImage("images/enemy09.png");
		speed = 2;
		hp = 15;
		this.items = new Item[]{
				new Item005(mypanel),
				new Item004(mypanel),
				new Item003(mypanel),
				new Item001(mypanel),
				new Item002(mypanel)
		};
	}

}
