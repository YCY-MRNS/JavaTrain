package com.ycy.test8;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import org.w3c.dom.UserDataHandler;

public class Login extends JFrame {
	public static String sql = "select * from user;";
	private JTextField nameFiled = null;
	private JPasswordField pwFiled = null;
	private static User user;
	static JDBC jdbc = null;
	static Login lognBox = null;
	public static LinkedList<User> users = new LinkedList<>();

	public static void main(String[] args) {

		lognBox = new Login();
		lognBox.setVisible(true);
		jdbc = new JDBC();

	}

	public Login() {
		super();

		setTitle("µÇÂ½");
		getContentPane().setLayout(null);
		setResizable(false);
		setBounds(100, 100, 300, 300);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((screenSize.width / 2) - (getWidth() / 2), (screenSize.height / 2) - (getHeight() / 2));

		JLabel name = new JLabel("ÐÕÃû£º");
		name.setFont(new Font("ËÎÌå", Font.BOLD, 20));
		name.setBounds(80, 60, 100, 50);
		getContentPane().add(name);

		nameFiled = new JTextField();
		nameFiled.setBounds(150, 75, 100, 25);
		getContentPane().add(nameFiled);

		JLabel label = new JLabel("ÃÜÂë£º");
		label.setFont(new Font("ËÎÌå", Font.BOLD, 20));
		label.setBounds(80, 90, 100, 50);
		getContentPane().add(label);

		pwFiled = new JPasswordField();
		pwFiled.setBounds(150, 105, 100, 25);
		getContentPane().add(pwFiled);

		JButton loginBtn = new JButton();
		loginBtn.setText("µÇÂ½");
		loginBtn.setBounds(50, 185, 70, 25);
		getContentPane().add(loginBtn);
		loginBtn.addActionListener(new LoginActionListener());

		JButton registerBtn = new JButton();
		registerBtn.setText("×¢²á");
		registerBtn.setBounds(170, 185, 70, 25);
		getContentPane().add(registerBtn);
		registerBtn.addActionListener(new RegisterActionListener());

	}

	private class LoginActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			users = jdbc.queryAccount(nameFiled.getText());

			if (users.size() == 0) {
				JOptionPane.showMessageDialog(null, "ÎÞ´ËÓÃ»§£¡");
			} else {
				for (User user : users) {
					if (nameFiled.getText().equals(user.getName()) && pwFiled.getText().equals(user.getPassword())) {
						JOptionPane.showMessageDialog(null, "µÇÂ¼³É¹¦£¡");
						break;
					} else {
						JOptionPane.showMessageDialog(null, "ÃÜÂë´íÎó£¡");
						break;
					}
				}
			}
			users.clear();
		}
	}

	public class RegisterActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			lognBox.setVisible(false);
			new Register();

		}

	}
}
