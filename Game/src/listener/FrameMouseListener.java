package listener;

import view.BaseFrame;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class FrameMouseListener implements MouseListener {
    public BaseFrame baseFrame;
    @Override
    public void mouseClicked(MouseEvent e) {
        this.baseFrame.myPanel.player.x=e.getX()-this.baseFrame.myPanel.player.width/2;
        this.baseFrame.myPanel.player.y=e.getY()-this.baseFrame.myPanel.player.height/2;
    }


    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
