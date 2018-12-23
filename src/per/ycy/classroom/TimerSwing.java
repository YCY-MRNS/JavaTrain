package per.ycy.classroom;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class TimerSwing extends JFrame
{
	Timer timer;
	public void init()
	{
		setLayout(null);
		setSize(400,100);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

		timer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TimerSwing.this.setTitle(new java.util.Date().toString());
			}
		});
		timer.start();
	}

	public static void main(String[] args) 
	{
		new TimerSwing().init();
	}
}


