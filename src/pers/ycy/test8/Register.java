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

public class Register extends JFrame {
	static JDBC jdbc = null;
	private JTextField nameFiled;
	private JTextField nickNameFiled;
	private JPasswordField pwFiled;
	private LinkedList<User> queryUsers;

	public static void main(String[] args) {
		new Register().setVisible(true);
	}

	public Register() {
		super();

		setVisible(true);
		setTitle("ע��");
		getContentPane().setLayout(null);
		setBounds(100, 100, 300, 300);
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((screenSize.width / 2) - (getWidth() / 2), (screenSize.height / 2) - (getHeight() / 2));

		JLabel name = new JLabel("������");
		name.setFont(new Font("����", Font.BOLD, 20));
		name.setBounds(80, 60, 100, 50);
		getContentPane().add(name);
		nameFiled = new JTextField();
		nameFiled.setBounds(150, 75, 100, 25);
		getContentPane().add(nameFiled);

		JLabel nickName = new JLabel("�ǳƣ�");
		nickName.setFont(new Font("����", Font.BOLD, 20));
		nickName.setBounds(80, 90, 100, 50);
		getContentPane().add(nickName);
		nickNameFiled = new JTextField();
		nickNameFiled.setBounds(150, 105, 100, 25);
		getContentPane().add(nickNameFiled);

		JLabel label = new JLabel("���룺");
		label.setFont(new Font("����", Font.BOLD, 20));
		label.setBounds(80, 120, 100, 50);
		getContentPane().add(label);
		pwFiled = new JPasswordField();
		pwFiled.setBounds(150, 135, 100, 25);
		getContentPane().add(pwFiled);

		JButton registerBtn = new JButton();
		registerBtn.setText("ע��");
		registerBtn.setBounds(50, 185, 70, 25);
		getContentPane().add(registerBtn);
		registerBtn.addActionListener(new CommitListener());

		JButton cancelBtn = new JButton();
		cancelBtn.setText("����");
		cancelBtn.setBounds(170, 185, 70, 25);
		getContentPane().add(cancelBtn);
		cancelBtn.addActionListener(new CancleListener());

	}

	public class CommitListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			jdbc = new JDBC();
			int loginNum = 0;
			queryUsers = jdbc.queryAccount(nameFiled.getText());
			System.out.println(queryUsers.size());

			if (queryUsers.size() == 0) {
				if (!nameFiled.getText().isEmpty() && !nickNameFiled.getText().isEmpty()
						&& !pwFiled.getText().isEmpty()) {
					if (pwFiled.getText().length() >= 6) {
						if (!judgeNum(pwFiled.getText())) {
							JOptionPane.showMessageDialog(null, "����ֻ��Ϊ���֣�");
						} else {
							try {
								jdbc.addAcccount(nameFiled.getText(), nickNameFiled.getText(), loginNum,
										pwFiled.getText());
							} catch (Exception e1) {
								e1.printStackTrace();
							}
							JOptionPane.showMessageDialog(null, "ע��ɹ���");
						}
					} else {
						JOptionPane.showMessageDialog(null, "���벻��С��6λ��");
					}
				} else {
					JOptionPane.showMessageDialog(null, "����/�ǳ�/���벻��Ϊ�գ�");
				}
			} else {
				JOptionPane.showMessageDialog(null, "���û����Ѵ��ڣ�");
			}

			queryUsers.clear();
		}
	}

	public class CancleListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			new Login().setVisible(true);
		}

	}

	public boolean judgeNum(String conntent) {
		for (int i = 0; i < conntent.length(); i++) {
			if (!Character.isDigit(conntent.charAt(i))) {
				return false;
			}
		}
		return true;
	}

}
