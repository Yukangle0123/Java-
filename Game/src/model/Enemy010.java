package model;

import view.BaseFrame;
import view.MyPanel;

import java.awt.Toolkit;



public class Enemy010 extends Enemy{

	public Enemy010(MyPanel mypanel) {
		super(mypanel);
		width = 130;
		height = 100;
		x = (int)(Math.random() * (BaseFrame.frameWidth - width) );
		y = 0 - height;
		image = Toolkit.getDefaultToolkit().getImage("images/enemy10.png");
		speed = 3;
		hp = 18;
		this.items = new Item[]{
				new Item005(mypanel),
				new Item002(mypanel),
				new Item002(mypanel),
				new Item002(mypanel),
				new Item002(mypanel)
		};
	}

}
