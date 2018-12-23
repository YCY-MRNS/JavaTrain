package pers.ycy.test7;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class ClientFrame extends JFrame {
    private JTextField field; // 信息发送文本框
    private JLabel label; // 显示用户名
    private JPanel panel;// 下方面板
    private JTextArea area;// 信息接收文本域
    private JButton button;// 发送按钮
    private String userName;// 用户名称
    private ChatRoomClient client;// 客户端连接对象

    public ClientFrame() {

        do {
            try {
                String host = JOptionPane.showInputDialog(this, "请输入服务器IP地址");
                if (host == null) {
                    System.exit(0);
                }
                client = new ChatRoomClient(host, 4569);
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "网络无法连接，请重新设置参数");
            }
        } while (client == null);
        String str = JOptionPane.showInputDialog(this, "请输入用户名:");
        userName = str.trim();
        field = new JTextField(25);
        label = new JLabel(userName);
        area = new JTextArea(10, 10);
        area.setEditable(false);
        button = new JButton("发送");
        panel = new JPanel();

        inti();
        addEventHandler();
    }

    public void inti() {
        JScrollPane jsp = new JScrollPane(area);
        this.setTitle("聊天室");
        this.add(jsp);
        panel.add(label);
        panel.add(field);
        panel.add(button);

        this.add(panel, BorderLayout.SOUTH);
    }

    public void showMe() {
        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        new ReadMessageThread().start();
    }

    public void addEventHandler() {
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                client.sendMessage(userName + ":" + field.getText());
                field.setText("");
            }
        });

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent atg0) {
                int op = JOptionPane.showConfirmDialog(ClientFrame.this,
                        "确定要退出聊天室吗？", "确定", JOptionPane.YES_NO_OPTION);
                if (op == JOptionPane.YES_OPTION) {
                    client.sendMessage("%EXIT%:" + userName);
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    client.close();
                    System.exit(0);
                }
            }
        });
    }

    private class ReadMessageThread extends Thread {

        public void run() {
            while (true) {
                String str = client.reciveMessage();
                area.append(str + "\n");
            }
        }
    }

    public static void main(String[] args) {
        new ClientFrame().showMe();
    }
}
