package per.ycy.classroom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
	
class MyFrame extends JFrame {
	private JButton btn = new JButton("start");
	private JLabel  lbl = new JLabel("");
	MyFrame(){
		this.setTitle("test InvokeLater");
		this.setSize(300, 200);
		lbl.setFont( new Font("Times New Rome",0,48) );
		lbl.setHorizontalAlignment( SwingConstants.CENTER );
		getContentPane().setLayout( new BorderLayout() );
		getContentPane().add(btn, BorderLayout.NORTH );
		getContentPane().add(lbl, BorderLayout.CENTER );

		btn.addActionListener(
				new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						new Thread(new Runnable() {
							@Override
							public void run() {
								for (int i = 200; i >= 0; i--) {
									final int j = i;
									SwingUtilities.invokeLater(new Runnable() {
										@Override
										public void run() {
											lbl.setText("" + j);
										}
									});
									try {
										Thread.sleep(200);
									} catch (Exception ignored) {
									}
								}
							}
						}).start();
					}
				}
		);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
class InvokeLaterDemo{
	public static void main( String... args){
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new MyFrame().setVisible(true);
			}
		});
	}
}
		
