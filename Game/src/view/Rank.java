package view;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Rank extends JFrame {

    public JPanel contentPane;
    public JTextField textField;
    public JTextField textField_1;
    public JTextField textField_2;
    public JTextField textField_3;
    public JTextField textField_4;

    /**
     * Launch the application.
     */

    /**
     * Create the frame.
     */
    public Rank(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.CENTER);

        textField = new JTextField();
        textField.setEditable(true);
        textField.setEnabled(false);
        textField.setText("");
        textField.setColumns(10);

        textField_1 = new JTextField();
        textField_1.setEnabled(false);
        textField_1.setColumns(10);

        textField_2 = new JTextField();
        textField_2.setEnabled(false);
        textField_2.setColumns(10);

        textField_3 = new JTextField();
        textField_3.setEnabled(false);
        textField_3.setColumns(10);

        textField_4 = new JTextField();
        textField_4.setEnabled(false);
        textField_4.setColumns(10);

        JLabel lblNewLabel = new JLabel("First");

        JLabel lblNewLabel_1 = new JLabel("Second");

        JLabel lblNewLabel_2 = new JLabel("Third");

        JLabel lblNewLabel_3 = new JLabel("Fourth");

        JLabel lblNewLabel_4 = new JLabel("Fifth");
        GroupLayout gl_panel = new GroupLayout(panel);
        gl_panel.setHorizontalGroup(
                gl_panel.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel.createSequentialGroup()
                                .addGap(73)
                                .addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
                                        .addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblNewLabel_4, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE))
                                .addGap(26)
                                .addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
                                        .addComponent(textField_4, Alignment.LEADING)
                                        .addComponent(textField_3, Alignment.LEADING)
                                        .addComponent(textField_2, Alignment.LEADING)
                                        .addComponent(textField_1, Alignment.LEADING)
                                        .addComponent(textField, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE))
                                .addContainerGap(192, Short.MAX_VALUE))
        );
        gl_panel.setVerticalGroup(
                gl_panel.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblNewLabel))
                                .addGap(22)
                                .addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblNewLabel_1))
                                .addGap(18)
                                .addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblNewLabel_2))
                                .addGap(28)
                                .addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblNewLabel_3))
                                .addGap(29)
                                .addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblNewLabel_4))
                                .addContainerGap(41, Short.MAX_VALUE))
        );
        panel.setLayout(gl_panel);
    }
}
