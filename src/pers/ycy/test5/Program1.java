package pers.ycy.test5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Program1 extends JFrame {
    //姓名输入框
    private final JTextField nameField;
    //密码输入框
    private final JPasswordField passwordField;

    public static void main(String[] args) {
        Program1 frame = new Program1();
        frame.setVisible(true);
    }

    private Program1() {
        super();
        setTitle("登录");
        setBounds(100, 100, 260, 210);
        getContentPane().setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screenSize.width / 2) - (getWidth() / 2), (screenSize.height / 2) - (getHeight() / 2));

        JLabel name = new JLabel();
        name.setText("用户名：");
        name.setBounds(38, 30, 60, 15);
        getContentPane().add(name);

        nameField = new JTextField();
        nameField.setBounds(100, 30, 120, 21);
        getContentPane().add(nameField);

        JLabel password = new JLabel();
        password.setText("密码：");
        password.setBounds(38, 83, 60, 15);
        getContentPane().add(password);

        passwordField = new JPasswordField();
        passwordField.setBounds(100, 80, 120, 21);
        getContentPane().add(passwordField);

        JButton loginBtn = new JButton();
        loginBtn.setText("登录");
        loginBtn.setBounds(60, 130, 60, 25);
        getContentPane().add(loginBtn);
        loginBtn.addActionListener(new login());

        JButton cancelBtn = new JButton();
        cancelBtn.setText("取消");
        cancelBtn.setBounds(150, 130, 60, 25);
        getContentPane().add(cancelBtn);


    }

    private class login implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String jPassword = new String(passwordField.getPassword());
            if (nameField.getText().equals("1") && jPassword.equals("11")) {
                JOptionPane.showMessageDialog(null, "登陆成功！", "登陆", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "登陆失败（账号或者密码失败）！", "登陆", JOptionPane.INFORMATION_MESSAGE);
            }

        }
    }
}
