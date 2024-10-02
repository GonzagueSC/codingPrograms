import java.awt.Color;

import javax.swing.*;

public class Frame extends JFrame{
	static JFrame th;
	static JPanel curr;
	static JPanel prev;
	public Frame() {
		th = this;
		this.setBounds(0, 0, 800, 800);
		this.setVisible(true);
		this.setResizable(false);
	}
	public void removeCurr() { 
		
	}
	public void changePanel(JPanel Panel) {
		prev = curr;
		curr = Panel;
		th.add(curr);
	}
}
