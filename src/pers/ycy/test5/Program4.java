package pers.ycy.test5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Program4 extends JFrame {
    //要发送的内容
    private final JTextField message;
    // 显示的文本域
    private final JTextArea content;
    //格式日期
    private SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");

    public static void main(String[] args) {
        new Program4().setVisible(true);
    }

    private Program4() {
        super();
        setTitle("聊天窗口");
        setBounds(100, 100, 590, 400);
        getContentPane().setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screenSize.width / 2) - (getWidth() / 2), (screenSize.height / 2) - (getHeight() / 2));

        content = new JTextArea();
        content.selectAll();
        content.setFont(new Font("微软雅黑", Font.ITALIC, 18));

        JScrollPane scrollPane = new JScrollPane(content);
        scrollPane.setBounds(10, 10, 550, 300);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        getContentPane().add(scrollPane);

        message = new JTextField();
        message.setBounds(10, 320, 480, 30);
        getContentPane().add(message);

        JButton sendBtn = new JButton();
        sendBtn.setText("发送");
        sendBtn.setBounds(500, 320, 60, 28);
        getContentPane().add(sendBtn);
        sendBtn.addActionListener(new sendMessage());

        showContent();
    }

    private void showContent() {

        try {
            File file = new File("C://", "test.txt");
            if (!file.exists()) file.createNewFile();
            FileReader fr = new FileReader(new File("C://", "test.txt"));
            BufferedReader br = new BufferedReader(fr);
            String readLine = "";

            while ((readLine = br.readLine()) != null) {
                content.append(readLine + "\n");
            }

            if (file.length() != 0) {
                content.append("-----以上是历史消息-----" + "\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private class sendMessage implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //将光标移动到最后
            content.selectAll();
            if (message.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "不能发送空消息！");
            } else {
                save(format.format(new Date()) + "\n" + message.getText() + "\n");
                content.append("\n" + format.format(new Date()) + "\n" + message.getText() + "\n");
                message.setText("");
            }
        }
    }

    private void save(String s) {
        try {
            File file = new File("C://", "test.txt");
            if (!file.exists()) file.createNewFile();

            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(s + "\n");
            bw.flush();
            bw.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
