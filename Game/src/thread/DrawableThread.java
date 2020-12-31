package thread;

import view.Msg;
import view.MyPanel;

public class DrawableThread extends Thread {
    public static boolean isStop;
    public MyPanel myPanel;
    public DrawableThread(MyPanel myPanel){
        this.myPanel=myPanel;
    }

    @Override
    public void run() {
        while(true){
            if (isStop == true) {
                //弹出游戏结束消息框
                Msg msg=new Msg(myPanel.baseFrame);
                break;
            }
            this.myPanel.repaint();//重绘
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
