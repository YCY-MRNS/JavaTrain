package pers.ycy.test5;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;

public class Program5 extends JFrame {
    public static void main(String[] args) {
        Program5 program5 = new Program5();
        program5.setVisible(true);
    }

    private Program5() {
        super();
        setTitle("计算器");
        setResizable(false);
        setBounds(100, 100, 222, 322);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screenSize.width / 2) - (getWidth() / 2), (screenSize.height / 2) - (getHeight() / 2));

        JMenuBar jMenuBar = new JMenuBar();
        setJMenuBar(jMenuBar);

        jMenuBar.add(new JMenu("查看(V)"));
        jMenuBar.add( new JMenu("编辑(E)"));
        jMenuBar.add( new JMenu("帮助(H)"));

        JPanel panel = new JPanel();
        panel.setBackground(new Color(217,228,241));
        getContentPane().add(panel, BorderLayout.NORTH);
        JTextField textField = new JTextField("0");
        textField.setEditable(false);
        textField.setDisabledTextColor(Color.BLACK);
        textField.setBorder(new MatteBorder(1, 1, 1, 1, new Color(142,156,173)));
        textField.setFont(new Font("宋体", Font.PLAIN, 21));
        textField.setPreferredSize(new Dimension(180, 50));
        textField.setHorizontalAlignment(SwingConstants.RIGHT);
        textField.setColumns(18);
        panel.add(textField);

        JPanel btnPanel = new JPanel();
        btnPanel.setBackground(new Color(217,228,241));
        GridLayout gridLayout = new GridLayout(5, 0);
        gridLayout.setVgap(10);
        gridLayout.setHgap(10);
        btnPanel.setLayout(gridLayout);

        getContentPane().add(btnPanel, BorderLayout.CENTER);
        String[][] name = {{"MC", "MR", "MS", "M+", "M-"}, {"<-", "CE", "C", "±", "√"},
                {"7", "8", "9", "/", "%"}, {"4", "5", "6", "*", "1/x"}, {"1", "2", "3", "-", "="}};
        JButton[][] buttons = new JButton[5][5];

        for (int row = 0; row < name.length; row++) {
            for (int col = 0; col < name.length; col++) {
                buttons[row][col] = new JButton(name[row][col]);
                buttons[row][col].setMargin(new Insets(0, 0, 0, 0));
                buttons[row][col].setFont(new Font("宋体", Font.PLAIN, 12));
                btnPanel.add(buttons[row][col]);
            }
        }

        JLabel left = new JLabel();
        left.setPreferredSize(new Dimension(10, 0));
        left.setBackground(new Color(217,228,241));
        getContentPane().add(left, BorderLayout.WEST);
        JLabel right = new JLabel();
        right.setBackground(new Color(217,228,241));
        right.setPreferredSize(new Dimension(10, 0));
        getContentPane().add(right, BorderLayout.EAST);
        JLabel bottom = new JLabel();
        bottom.setBackground(new Color(217,228,241));
        bottom.setPreferredSize(new Dimension(0, 10));
        getContentPane().add(bottom, BorderLayout.SOUTH);

    }
}
