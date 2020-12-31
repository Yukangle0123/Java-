package view;
import JDBC.User;
import JDBC.UserDao;

import java.util.Arrays;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Msg extends JFrame  {

    public BaseFrame baseFrame;
    public JLabel msgLabel;
    public JButton confirmButtom;
    public JButton button;

    public JButton cancelButton;

    public Msg(BaseFrame baseFrame) {
    	this.baseFrame=baseFrame;
        this.setTitle("游戏结束");
        this.confirmButtom=new JButton("继续游戏");
        this.cancelButton=new JButton("退出");
        this.button=new JButton("排行榜");

        this.msgLabel=new JLabel("Game Over！！");

        setLayout(new GridLayout(2, 1));
        JPanel jp1 = new JPanel();
        jp1.add(msgLabel);
        this.add(jp1);

        JPanel jp2 = new JPanel();
        jp2.add(confirmButtom);
        jp2.add(cancelButton);
        jp2.add(button);
        this.add(jp2);

        this.setSize(300,200);
        this.setVisible(true);

        this.setLocationRelativeTo(baseFrame);

        confirmButtom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            	//关闭原游戏窗口
               Msg.this.baseFrame.dispose();

               Msg.this.dispose();

               Msg.this.setVisible(false);
               
               //新开游戏窗口
               
               new BaseFrame();
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserDao userDao=new UserDao();
                List<Integer> scores=userDao.selectAllLevel();
                for(int i=0;i<scores.size();i++){
                    System.out.println("第"+(i+1)+"名:"+scores.get(i));
                }
                if(baseFrame.myPanel.player.count<scores.get(4)){
                    System.out.println("你还要继续努力哦");
                }
                if(baseFrame.myPanel.player.count>scores.get(0)){
                    System.out.println("真棒，傲视群雄");
                }
                System.exit(0);
            }
        });
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Rank rank=new Rank();
                UserDao userDao=new UserDao();
                List<Integer> scores=userDao.selectAllLevel();

                rank.textField.setText(scores.get(0)+"");
                rank.textField_1.setText(scores.get(1)+"");
                rank.textField_2.setText(scores.get(2)+"");
                rank.textField_3.setText(scores.get(3)+"");
                rank.textField_4.setText(scores.get(4)+"");
                rank.setVisible(true);
            }
        });
    }
}
