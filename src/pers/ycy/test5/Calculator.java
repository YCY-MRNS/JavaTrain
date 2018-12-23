package pers.ycy.test5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame {

	private String num = "0";// ������

	private String operator = "+";// �����

	private String result = "0";// ��������

	private final JTextField textField;

	public static void main(String args[]) {
		Calculator frame = new Calculator();
		frame.setVisible(true); // ���ô���ɼ���Ĭ��Ϊ���ɼ�
	}

	public Calculator() {
		super();
		setTitle("������");
		setResizable(false); // ���ô����С���ɸı�
		setBounds(100, 100, 208, 242);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		final JPanel viewPanel = new JPanel(); // ��������ʾ������壬����Ĭ�ϵ�������
		getContentPane().add(viewPanel, BorderLayout.NORTH); // ������ʾ���������ӵ����嶥��

		textField = new JTextField(); // ��������ʾ����
		textField.setText(num);// ���á���ʾ������Ĭ���ı�
		textField.setColumns(18);// ���á���ʾ�����Ŀ��
		textField.setEditable(false); // ���á���ʾ�������ɱ༭
		textField.setHorizontalAlignment(SwingConstants.RIGHT);// ���á���ʾ�����ı����Ҳ����
		viewPanel.add(textField); // ������ʾ������ӵ�����ʾ���������
		getContentPane().add(viewPanel, BorderLayout.NORTH); // ������ʾ���������ӵ����嶥��

		final JPanel clearButtonPanel = new JPanel();// ���������ť�ް壬����Ĭ�ϵ�������
		getContentPane().add(clearButtonPanel, BorderLayout.CENTER); // �������ť�����ӵ������м�

		String[] clearButtonNames = { "  <��  ", "  CE  ", "   C   " };// ���������ť��������
		for (int i = 0; i < clearButtonNames.length; i++) {
			final JButton button = new JButton(clearButtonNames[i]);// ���������ť
			button.addActionListener(new ClearButtonActionListener());// Ϊ�����ť����¼�������
			clearButtonPanel.add(button);// �������ť��ӵ������ť�����
		}

		final JPanel inpuptButtonPanel = new JPanel(); // �������밴ť���
		final GridLayout gridLayout = new GridLayout(4, 0);
		gridLayout.setVgap(10);
		gridLayout.setHgap(10);
		inpuptButtonPanel.setLayout(gridLayout); // ���밴ť���������񲼾�
		getContentPane().add(inpuptButtonPanel, BorderLayout.SOUTH); // �����밴ť�����ӵ�����ײ�

		String[][] inputButtonNames = { { "1", "2", "3", "+" },
				{ "4", "5", "6", "-" }, { "7", "8", "9", "*" },
				{ ".", "0", "=", "/" } };// �������밴ť��������
		for (int row = 0; row < inputButtonNames.length; row++) {
			for (int col = 0; col < inputButtonNames.length; col++) {
				final JButton button = new JButton(inputButtonNames[row][col]);// �������밴ť
				button.setName(row + "" + col);// �������밴ť�����ƣ����������к��е��������
				button.addActionListener(new InputButtonActionListener());// Ϊ���밴ť����¼�������
				inpuptButtonPanel.add(button); // ����ť��ӵ���ť�����
			}
		}
	}

	class ClearButtonActionListener implements ActionListener {// �����ť�¼�������
		public void actionPerformed(ActionEvent e) {
			JButton button = (JButton) e.getSource();// ��ô����˴��¼��İ�ť����
			String text = button.getText().trim();// ��ð�ť���ı�
			if (text.equals("<��")) {// ��������������ְ�ť
				int length = num.length();
				if (length == 1)// ��������ֵΪ1λ����ʱ
					num = "0";// ����ΪĬ��ֵ0
				else
					num = num.substring(0, length - 1);// ����ȥ��������ֵ�����һλ����
			} else if (text.equals("CE")) {// �����ǰ������ֵ��ť
				num = "0";
			} else {// ִ���µļ��㰴ť
				num = "0";
				operator = "+";
				result = "0";
			}
			textField.setText(num);// �޸ġ���ʾ�����ı�
		}
	}

	class InputButtonActionListener implements ActionListener {// ���밴ť�¼�������
		public void actionPerformed(ActionEvent e) {
			JButton button = (JButton) e.getSource();// ��ô����˴��¼��İ�ť����
			String name = button.getName();// ��ð�ť������
			int row = Integer.valueOf(name.substring(0, 1));// ���������ڵ���
			int col = Integer.valueOf(name.substring(1, 2));// ���������ڵ���
			if (col == 3) {// �˴������Ϊ�����
				count();// ������
				textField.setText(result);// �޸ġ���ʾ�����ı�
				num = "0";
				operator = button.getText();// �������������
			} else if (row == 3) {// �˴������Ϊ��.������0����=��
				if (col == 0) {// �˴������Ϊ��.��
					if (num.indexOf(".") < 0) {// �鿴�Ƿ��Ѿ�������С����
						num = num + button.getText();
						textField.setText(num);
					}
				} else if (col == 1) {// �˴������Ϊ��0��
					if (num.indexOf(".") > 0) {// �鿴�Ƿ��Ѿ�������С����
						num = num + button.getText();
						textField.setText(num);
					} else {
						if (!num.substring(0, 1).equals("0")) {// �鿴������ֵ�ĵ�һλ�Ƿ�Ϊ0
							num = num + button.getText();
							textField.setText(num);
						}
					}
				} else {// �˴������Ϊ��=��
					count();// ������
					textField.setText(result);// �޸ġ���ʾ�����ı�
					num = "0";
					operator = "+";// �������������
				}
			} else {// �˴������Ϊ����
				if (num.equals("0"))
					num = button.getText();
				else
					num = num + button.getText();
				textField.setText(num);
			}
		}

		private void count() {// ������
			float n = Float.valueOf(num);
			float r = Float.valueOf(result);
			if (r == 0) {
				result = num;
				num = "0";
			} else {
				if (operator.equals("+")) {
					r = r + n;
				} else if (operator.equals("-")) {
					r = r - n;
				} else if (operator.equals("*")) {
					r = r * n;
				} else {
					r = r / n;
				}
				num = "0";
				result = r + "";
			}
		}
	}

}
