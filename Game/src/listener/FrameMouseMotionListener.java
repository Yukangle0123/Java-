package listener;

import view.BaseFrame;
import view.MyPanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;



public class FrameMouseMotionListener implements MouseMotionListener, MouseListener {
	
	public BaseFrame baseFrame;
	MyPanel myPanel;
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		baseFrame.myPanel.player.x=e.getX()-baseFrame.myPanel.player.width/2;
		baseFrame.myPanel.player.y=e.getY()-baseFrame.myPanel.player.height/2;
		
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseClicked(MouseEvent e) {
//		switch (MyPanel.state){
//			case MyPanel.START:          //启动状态时
//				MyPanel.state = MyPanel.RUNNING; //修改为运行状态
//				break;
//			case  MyPanel.GAME_OVER:
//				 myPanel.player.count=0;
//		}
		MyPanel.state = MyPanel.RUNNING;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(MyPanel.state==MyPanel.RUNNING){
			MyPanel.state=MyPanel.PAUSE;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if(MyPanel.state==MyPanel.PAUSE){
			MyPanel.state=MyPanel.RUNNING;
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if(MyPanel.state==MyPanel.RUNNING){
			MyPanel.state=MyPanel.PAUSE;
		}
	}
}
