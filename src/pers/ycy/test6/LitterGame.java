package pers.ycy.test6;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class LitterGame extends JFrame {
    //用数组存放大小字母
    private static String[] a = {"a", "b", "c", "d", "q", "z", "w", "x", "s", "e", "r", "t", "f", "y", "h",
            "n", "u", "j", "m", "k", "o", "p", "A", "B", "C", "D", "Q", "Z", "W", "X", "S", "E", "R", "T",
            "F", "Y", "H", "N", "U", "J", "M", "K", "O", "P"};
    private static JLabel letter;
    private static JLabel result;
    //初始化分数
    private static int get = 0;
    private static JTextField content;

    public static void main(String[] args) {
        LitterGame litterGame = new LitterGame();
        litterGame.setVisible(true);
        //创建线程每两秒钟更新字母
        new Thread(new LitterChange()).start();
    }

    private LitterGame() {
        super();
        setTitle("打字母游戏");
        getContentPane().setLayout(null);
        setResizable(false);
        setBounds(100, 100, 450, 120);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(screenSize.width / 2 - getWidth() / 2, screenSize.height / 2 - getHeight() / 2);

        JLabel label = new JLabel();
        label.setText("显示字母：");
        label.setBounds(40, 20, 80, 40);
        getContentPane().add(label);

        letter = new JLabel();
        letter.setText("g");
        letter.setFont(new Font("微软雅黑", Font.BOLD, 24));
        letter.setForeground(Color.red);
        letter.setBounds(110, 15, 40, 40);
        getContentPane().add(letter);

        JLabel label2 = new JLabel();
        label2.setText("输入所显示的字母(回车)");
        label2.setBounds(140, 20, 150, 40);
        getContentPane().add(label2);

        content = new JTextField();
        content.setBounds(310, 30, 55, 20);
        getContentPane().add(content);

        JLabel resultText = new JLabel();
        resultText.setText("得分:");
        resultText.setBounds(370, 20, 40, 40);
        getContentPane().add(resultText);

        result = new JLabel();
        result.setText("0");
        result.setFont(new Font("微软雅黑", Font.BOLD, 20));
        result.setForeground(Color.red);
        result.setBounds(410, 20, 40, 40);
        getContentPane().add(result);

        //给输入框添加键盘的监听
        content.addKeyListener(new enter());

    }

    private static class LitterChange implements Runnable {
        @Override
        public void run() {
            try {
                while (true) {
                    //延时2秒
                    Thread.sleep(2000);
                    letter.setText(a[(int) (Math.random() * a.length)]);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static class enter implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            //按下回车键触发事件
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                //判断是否相等
                if (content.getText().equals(letter.getText())) {
                    //分数累加
                    get += 2;
                    result.setText(get + "");
                }
                //重置输入框
                content.setText("");
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }
}
