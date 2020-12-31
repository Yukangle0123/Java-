package view;

import JDBC.User;
import JDBC.UserDao;
import util.GameException;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class LoginFrame extends JFrame {

	private JPanel contentPane;
	private JTextField userNameTxt;
	private JPasswordField passwordTxt;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					LoginFrame frame = new LoginFrame ();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginFrame() {
//		setIconImage(Toolkit.getDefaultToolkit().getImage());
		setResizable(false);
//		setTitle("µÇÂ¼");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 562, 406);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel_1 = new JLabel(" \u7528 \u6237 \u540D");
		//lblNewLabel_1.setIcon(new ImageIcon("D:\\\u4F5C\u4E1A\\java\\BookManage\\image\\userName.png"));
		
		JLabel lblNewLabel_2 = new JLabel(" \u5BC6   \u7801");
		//lblNewLabel_2.setIcon(new ImageIcon("D:\\\u4F5C\u4E1A\\java\\BookManage\\image\\password.png"));
		
		userNameTxt = new JTextField();
		userNameTxt.setColumns(10);
		
		passwordTxt = new JPasswordField();
		
		lblNewLabel = new JLabel("·É»ú´óÕ½");
		lblNewLabel.setFont(new Font("ËÎÌå", Font.PLAIN, 19));
		//lblNewLabel.setIcon(new ImageIcon("D:\\\u4F5C\u4E1A\\java\\BookManage\\image\\book.png"));
		
		JButton btnNewButton = new JButton("µÇÂ¼");
	//	btnNewButton.setIcon(new ImageIcon("D:\\\u4F5C\u4E1A\\java\\BookManage\\image\\login (2).png"));
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				login1();
			}
		});
		
		JButton btnNewButton_1 = new JButton("×¢²á");
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				register();
			}
		});
		//btnNewButton_1.setIcon(new ImageIcon("D:\\\u4F5C\u4E1A\\java\\BookManage\\image\\reset.png"));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 257, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(281, Short.MAX_VALUE))
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGap(99)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
							.addGap(76)
							.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
							.addGap(150))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
								.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(passwordTxt, GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
								.addComponent(userNameTxt, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE))
							.addGap(71))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(20)
					.addComponent(lblNewLabel)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(userNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1))
					.addGap(54)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(passwordTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_2))
					.addPreferredGap(ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
					.addGap(89))
		);
		contentPane.setLayout(gl_contentPane);
		setVisible(true);
	}
/**
 * µÇÂ¼
 */
	protected void login1() {
		String username = userNameTxt.getText();
		String password=new String(passwordTxt.getPassword());
		UserDao userDao=new UserDao();
		try {
			User existUser = userDao.selectByName(username);
			if(existUser==null||!existUser.getPassword().equals(password)){
				JOptionPane.showMessageDialog(null,"ÓÃ»§Ãû»òÃÜÂë´íÎó");
				return;
			}
			this.dispose();
			new BaseFrame();

		} catch (GameException e) {
			e.printStackTrace();
		}
	}
/**
 * ×¢²á
 */
	protected void register() {
		String username = userNameTxt.getText();
		String password=new String(passwordTxt.getPassword());
		UserDao userDao=new UserDao();
		try {
			User existUser = userDao.selectByName(username);
			if(existUser!=null){
				JOptionPane.showMessageDialog(null,"¸ÃÓÃ»§ÒÑ×¢²á");
				return;
			}
			User user=new User();
			user.setName(username);
			user.setPassword(password);
			userDao.add(user);
			JOptionPane.showMessageDialog(null,"×¢²á³É¹¦ÇëµÇÂ¼");
		} catch (GameException e) {
			e.printStackTrace();
		}
	}
}
