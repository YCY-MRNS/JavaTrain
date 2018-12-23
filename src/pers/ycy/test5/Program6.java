package pers.ycy.test5;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Program6 extends JFrame {
    private final JPanel centerPanel;
    private JButton emptyButton;

    public static void main(String[] args) {
        new Program6().setVisible(true);
    }

    private Program6() {
        super();

        setResizable(false);
        setTitle("拼图游戏");
        setBounds(100, 100, 370, 525);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final JPanel topPanel = new JPanel();
        topPanel.setBorder(new TitledBorder(null, "",
                TitledBorder.DEFAULT_JUSTIFICATION,
                TitledBorder.DEFAULT_POSITION, null, null));
        topPanel.setLayout(new BorderLayout());
        getContentPane().add(topPanel, BorderLayout.NORTH);

        final JLabel modelLabel = new JLabel();
        modelLabel.setIcon(getImageIcon("src/img/Koala.jpg", 204, 152));
        topPanel.add(modelLabel, BorderLayout.WEST);

        final JButton startButton = new JButton();
        startButton.setText("下一局");
        startButton.addActionListener(new StartButtonAction());
        topPanel.add(startButton, BorderLayout.CENTER);

        centerPanel = new JPanel();
        centerPanel.setBorder(new TitledBorder(null, "",
                TitledBorder.DEFAULT_JUSTIFICATION,
                TitledBorder.DEFAULT_POSITION, null, null));
        centerPanel.setLayout(new GridLayout(0, 3));

        getContentPane().add(centerPanel, BorderLayout.CENTER);

        String[][] stochasticOrder = reorder();

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                final JButton button = new JButton();
                button.setName(row + "" + col);
                button.setIcon(getImageIcon(stochasticOrder[row][col], 118, 118));

                if (stochasticOrder[row][col].equals("src/img/00.jpg"))
                    emptyButton = button;
                button.addActionListener(new ImgButtonAction());
                centerPanel.add(button);


            }

        }
    }

    private ImageIcon getImageIcon(String file, int w, int h) {
        ImageIcon icon = new ImageIcon(file);
        icon.setImage(icon.getImage().getScaledInstance(w, h, Image.SCALE_DEFAULT));
        return icon;
    }

    private String[][] reorder() {
        String[][] exactness = new String[3][3];
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                exactness[row][col] = "src/img/" + row + col + ".jpg";

            }
        }

        String[][] sto = new String[3][3];
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                while (sto[row][col] == null) {
                    int r = (int) (Math.random() * 3);
                    int c = (int) (Math.random() * 3);
                    if (exactness[r][c] != null) {
                        sto[row][col] = exactness[r][c];
                        exactness[r][c] = null;
                    }

                }
            }
        }

        return sto;
    }

    private class ImgButtonAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            String emptyName = emptyButton.getName();
            char r = emptyName.charAt(0);
            char c = emptyName.charAt(1);
            JButton click = (JButton) e.getSource();
            String name = click.getName();
            char cr = name.charAt(0);
            char cc = name.charAt(1);

            if (Math.abs(cr - r) + Math.abs(cc - c) == 1) {
                emptyButton.setIcon(click.getIcon());
                click.setIcon(getImageIcon("src/img/00.jpg", 118, 118));
                emptyButton = click;
            }
        }
    }

    class StartButtonAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String[][] sto = reorder();
            int i = 0;
            for (int row = 0; row < 5; row++) {
                for (int col = 0; col < 5; col++) {
                    JButton btn = (JButton) centerPanel.getComponent(i++);
                    btn.setIcon(getImageIcon(sto[row][col], 118, 118));
                    if (sto[row][col].equals("src/img/00.jpg")) {
                        emptyButton = btn;
                    }
                }
            }
        }
    }
}