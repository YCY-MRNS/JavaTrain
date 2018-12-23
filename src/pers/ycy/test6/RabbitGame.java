package pers.ycy.test6;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RabbitGame extends JFrame {

    //兔子
    private final JLabel rabbit;
    //移动量
    private static int move = 0;
    //控制兔子移动的线程
    private final Thread run;


    public static void main(String[] args) {
        new RabbitGame().setVisible(true);
    }

    private RabbitGame() {
        super();
        setTitle("聊天窗口");
        setBounds(100, 100, 600, 400);
        getContentPane().setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screenSize.width / 2) - (getWidth() / 2), (screenSize.height / 2) - (getHeight() / 2));

        rabbit = new JLabel();
        rabbit.setBounds(0, 100, 200, 200);
        rabbit.setIcon(getImageIcon("src/img/rabbit.png", 80, 80));
//        rabbit.setIcon(new ImageIcon("src/img.rabbit.png"));
        getContentPane().add(rabbit);

        JButton startBtn = new JButton();
        startBtn.setText("开始");
        startBtn.setBounds(140, 320, 60, 28);
        getContentPane().add(startBtn);

        JButton stopBtn = new JButton();
        stopBtn.setText("停止");
        stopBtn.setBounds(400, 320, 60, 28);
        getContentPane().add(stopBtn);

        //开始按钮的监听
        startBtn.addActionListener(new startListener());
        //停止按钮的监听
        stopBtn.addActionListener(new stopListener());

        run = new Thread(new rabbitRun());

    }

    private class startListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            run.start();
        }
    }

    private ImageIcon getImageIcon(String file, int w, int h) {
        ImageIcon icon = new ImageIcon(file);
        icon.setImage(icon.getImage().getScaledInstance(w, h, Image.SCALE_DEFAULT));
        return icon;
    }

    private class stopListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            run.suspend();
        }
    }

    private class rabbitRun implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(1000);
                    if (move < 500) {
                        move += 50;
                        rabbit.setBounds(move, 100, 200, 200);
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
