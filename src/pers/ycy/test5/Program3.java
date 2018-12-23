package pers.ycy.test5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class Program3 extends JFrame {
    //姓名的输入框
    private final JTextField nameFiled;
    //ID的输入框
    private final JTextField idFiled;
    //出生日期的输入框
    private final JTextField birthFiled;
    //保存的注册的学生信息的liSt
    private LinkedList<Student> students = new LinkedList<>();

    public static void main(String[] args) {
        Program3 program3 = new Program3();
        program3.setVisible(true);
    }

    private Program3() {
        super();
        setTitle("注册学生信息");
        getContentPane().setLayout(null);
        setBounds(100, 100, 300, 300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screenSize.width / 2) - (getWidth() / 2), (screenSize.height / 2) - (getHeight() / 2));


        JLabel name = new JLabel("姓名：");
        name.setFont(new Font("宋体", Font.BOLD, 20));
        name.setBounds(80, 60, 100, 50);
        getContentPane().add(name);

        nameFiled = new JTextField();
        nameFiled.setBounds(150, 75, 100, 25);
        getContentPane().add(nameFiled);

        JLabel label = new JLabel("学号：");
        label.setFont(new Font("宋体", Font.BOLD, 20));
        label.setBounds(80, 90, 100, 50);
        getContentPane().add(label);

        idFiled = new JTextField();
        idFiled.setBounds(150, 105, 100, 25);
        getContentPane().add(idFiled);

        JLabel birth = new JLabel("出生日期：");
        birth.setFont(new Font("宋体", Font.BOLD, 20));
        birth.setBounds(40, 120, 150, 50);
        getContentPane().add(birth);

        birthFiled = new JTextField();
        birthFiled.setBounds(150, 135, 100, 25);
        getContentPane().add(birthFiled);

        JButton register = new JButton();
        register.setText("注册");
        register.setBounds(50, 185, 70, 25);
        getContentPane().add(register);
        register.addActionListener(new registerListener());

        JButton cancel = new JButton();
        cancel.setText("取消");
        cancel.setBounds(170, 185, 70, 25);
        getContentPane().add(cancel);
        cancel.addActionListener(new cancelListener());

    }


    private class registerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            if (students.isEmpty()) {
                students.add(new Student(idFiled.getText(), nameFiled.getText(), birthFiled.getText()));
                JOptionPane.showMessageDialog(null, "注册成功！", "注册操作", JOptionPane.INFORMATION_MESSAGE);
            } else {
                for (Student student : students) {
                    if (student.getName().equals(nameFiled.getText())) {
                        JOptionPane.showMessageDialog(null, "已经注册！", "注册操作", JOptionPane.WARNING_MESSAGE);
                    } else {
                        students.add(new Student(idFiled.getText(), nameFiled.getText(), birthFiled.getText()));
                        JOptionPane.showMessageDialog(null, "注册！", "注册操作", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        }
    }


    private class cancelListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
}



