package pers.ycy.test5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Program2 extends JFrame {
    //第一个数字的输入框
    private final JTextField firstNum;
    //第二个数字的输入框
    private final JTextField secondNum;
    //结果的输入框
    private final JTextField result;

    public static void main(String[] args) {
        Program2 program2 = new Program2();
        program2.setVisible(true);
    }

    private Program2() {
        super();
        setTitle("加法计算器");
        setBounds(100, 100, 380, 200);
        getContentPane().setLayout(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screenSize.width / 2) - (getWidth() / 2), (screenSize.height / 2) - (getHeight() / 2));

        firstNum = new JTextField();
        firstNum.setBounds(50, 50, 70, 25);
        getContentPane().add(firstNum);

        JLabel label = new JLabel();
        label.setText("+");
        label.setFont(new Font("宋体", Font.BOLD, 20));
        label.setBounds(130, 35, 60, 60);
        getContentPane().add(label);

        secondNum = new JTextField();
        secondNum.setBounds(150, 50, 70, 25);
        getContentPane().add(secondNum);

        JButton equ = new JButton();
        equ.setText("=");
        equ.setBounds(225, 50, 50, 25);
        getContentPane().add(equ);
        equ.addActionListener(new equBtn());

        result = new JTextField();
        result.setBounds(280, 50, 70, 25);
        getContentPane().add(result);

    }

    private class equBtn implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            result.setText(Integer.parseInt(firstNum.getText()) + Integer.parseInt(secondNum.getText()) + "");
        }
    }
}
