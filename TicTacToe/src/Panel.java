import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Panel extends JPanel{
	public Panel() {
		this.setBounds(0, 0, 800, 800);
		this.setVisible(true);
		this.setBackground(new Color(150, 150, 150));
		JButton b0 = new JButton("");
		JButton b1 = new JButton("1 Player");
		JButton b2 = new JButton("2 Player");
		JLabel l = new JLabel("Tic-Tac-Toe", SwingConstants.CENTER);
		l.setBounds(120, 110, 560, 200);
		l.setFont(new Font(Font.SERIF, Font.HANGING_BASELINE, 80));
		b1.setFont(new Font(Font.SERIF, Font.CENTER_BASELINE, 30));
		b1.setBounds(300, 320, 200, 100);
		b2.setBounds(300, 480, 200, 100);
		b2.setFont(new Font(Font.SERIF, Font.CENTER_BASELINE, 30));
		b2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Main.TwoPlayer();
				
			}
			
		});
		b1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Main.OnePlayer();
				
			}
			
		});
		this.add(b0);
		this.add(b1);
		this.add(b2);
		this.add(l);
	}
}
